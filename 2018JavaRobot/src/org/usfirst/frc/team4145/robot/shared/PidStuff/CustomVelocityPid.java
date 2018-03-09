package org.usfirst.frc.team4145.robot.shared.PidStuff;

import edu.wpi.first.wpilibj.*;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team4145.robot.shared.HardwareTimer;

public class CustomVelocityPid {

    private Encoder m_EncoderInstance;
    //private PIDOutput m_PIDOutputInstance;
    private Trajectory m_Trajectory;
    private Notifier m_Notifier;
    private double kP, kI, kD, kV, kA;
    private int nominalDt = 5;
    private boolean isEnabled = false;


    private Trajectory.Segment setpoint;
    private double toWrite = 0.0, feedForward = 0.0, feedBack = 0.0, error = 0.0, errorDeriv = 0.0, errorLast = 0.0;
    private int pointer = 0;

    private Runnable runnable = () -> calculate();

    public CustomVelocityPid(double kP, double kI, double kD, double kV, double kA, Encoder encoder, Trajectory trajectory, double timing){
        m_EncoderInstance = encoder; //m_PIDOutputInstance = pidOutput;
        this.kP = kP; this.kI = kI; this.kD = kD; this.kV = kV; this.kA = kA;
        m_Trajectory = trajectory;
        m_Notifier = new Notifier(runnable);
        m_Notifier.startPeriodic(timing);
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

    private void calculate(){
        if(m_Trajectory != null){
            while (isEnabled) {
                setpoint = m_Trajectory.get(pointer);

                feedForward = setpoint.velocity * kV + setpoint.acceleration * kA;
                toWrite = feedForward; //set to feedforward

                error = setpoint.position - m_EncoderInstance.getDistance();
                errorDeriv = ((error - errorLast) / nominalDt) - setpoint.velocity;
                feedBack = kP * error + kD * errorDeriv;
                toWrite += feedBack; //add feedback

                //m_PIDOutputInstance.pidWrite(toWrite);
                errorLast = error;
                pointer++;
            }
            if(!isEnabled){
                pointer = 0;
                toWrite = 0;
            }
        }
    }
}
