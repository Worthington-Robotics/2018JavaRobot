package org.usfirst.frc.team4145.robot.shared.PidStuff;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomVelocityPid {

    private Encoder m_EncoderInstance;
    //private PIDOutput m_PIDOutputInstance;
    private Trajectory m_Trajectory;
    private Notifier m_Notifier;
    private double kP, kI, kD, kV, kA;
    private int nominalDt;
    private boolean isEnabled = false;
    private Trajectory.Segment setpoint;
    private double toWrite = 0.0, feedForward = 0.0, feedBack = 0.0, error = 0.0, errorDeriv = 0.0, errorLast = 0.0;
    private int index = 0;
    private static int instances = 0;
    private int instanceNum;
    private double offset;
    private int CalcNums = 0;

    private Runnable runnable = () -> calculate();

    public CustomVelocityPid(double kP, double kI, double kD, double kV, double kA, Encoder encoder, Trajectory trajectory, double timing, double offset){
        m_EncoderInstance = encoder; //m_PIDOutputInstance = pidOutput;
        this.kP = kP; this.kI = kI; this.kD = kD; this.kV = kV; this.kA = kA; this.offset = offset;
        nominalDt = (int)(timing * 1000);
        m_Trajectory = trajectory;
        m_Notifier = new Notifier(runnable);
        m_Notifier.startPeriodic(timing);
        instances++;
        instanceNum = instances;
    }

    public void enable(boolean enable){
        isEnabled = enable;
    }

    public void setProfile(Trajectory trajectory){
        m_Trajectory = trajectory;
    }

    public void free(){
        m_Notifier.stop();
    }

    public double getResult(){
        return toWrite;
    }

    public int getInstanceNum(){
        return instanceNum;
    }

    public static int getInstances(){
        return instances;
    }

    private void calculate(){
        if(m_Trajectory != null){
            if(isEnabled) {
                if(index/2 < m_Trajectory.length()) {   
                    //Every other iteration the feedForward runs
                    if(index % 2 == 0)
                    {
                    	//Calculate feed forward part of output
                    	setpoint = m_Trajectory.get(index/2);
                    feedForward = setpoint.velocity * kV + setpoint.acceleration * kA + offset;
                    toWrite = feedForward;
                    }
                    
                    //Calculate feed back part of output
                    error = setpoint.position - (m_EncoderInstance.getDistance() / 228); //228 counts per foot
                    errorDeriv = ((error - errorLast) / nominalDt) - setpoint.velocity;
                    feedBack = kP * error + kD * errorDeriv;
                    toWrite += feedBack;
                    
                    
                    SmartDashboard.putNumber("feed forward" + instanceNum, feedForward);
                    SmartDashboard.putNumber("feed back" + instanceNum, feedBack);
                    SmartDashboard.putNumber("Velocity" + instanceNum, toWrite);

                    //General cleanup for next iteration
                    errorLast = error;
                    index++;
                    
                }
                else{
                    toWrite = 0;
                }
            }
            else{
                index = 0;
                toWrite = 0;
            }
        }
    }
}
