package org.usfirst.frc.team4145.robot.shared.PidStuff;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

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

    private Runnable runnable = () -> calculate();

    public CustomVelocityPid(double kP, double kI, double kD, double kV, double kA, Encoder encoder, Trajectory trajectory, double timing){
        m_EncoderInstance = encoder; //m_PIDOutputInstance = pidOutput;
        this.kP = kP; this.kI = kI; this.kD = kD; this.kV = kV; this.kA = kA;
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
                setpoint = m_Trajectory.get(index);

                //Calculate feed forward part of output
                feedForward = setpoint.velocity * kV + setpoint.acceleration * kA;
                toWrite = feedForward;

                //Calculate feed back part of output
                error = setpoint.position - m_EncoderInstance.getDistance();
                errorDeriv = ((error - errorLast) / nominalDt) - setpoint.velocity;
                feedBack = kP * error + kD * errorDeriv;
                toWrite += feedBack;

                SmartDashboard.putNumber("feed forward" + instanceNum,feedForward);
                SmartDashboard.putNumber("feed back" + instanceNum,feedBack);
                SmartDashboard.putNumber("Velocity" + instanceNum,feedBack);
                SmartDashboard.putNumber("feed back" + instanceNum,feedBack);

                //General cleanup for next iteration
                errorLast = error;
                index++;
            }
            else{
                index = 0;
                toWrite = 0;
            }
        }
    }
}
