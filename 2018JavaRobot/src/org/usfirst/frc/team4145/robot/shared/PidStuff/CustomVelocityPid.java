package org.usfirst.frc.team4145.robot.shared.PidStuff;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team4145.robot.shared.HardwareTimer;

import java.util.concurrent.LinkedBlockingQueue;

public class CustomVelocityPid {

    private Encoder m_EncoderInstance;
    private PIDOutput m_PIDOutputInstance;
    private VelocitySetpoint[] m_Trajectory;
    private double kP, kI, kD, kV, kA;
    private int nominalDt = 5;
    private boolean keepRunning = true, isEnabled = false;

    public CustomVelocityPid(double kP, double kI, double kD, double kV, double kA, Encoder encoder, PIDOutput pidOutput, VelocitySetpoint[] trajectory){
        m_EncoderInstance = encoder;
        m_PIDOutputInstance = pidOutput;
        this.kP = kP; this.kI = kI; this.kD = kD; this.kV = kV; this.kA = kA;
        m_Trajectory = trajectory;
    }

    public void enable(boolean enable){
        isEnabled = enable;
    }

    public void free(){
        keepRunning = false;
    }

    private VelocitySetpoint[] localTrajectory;
    private VelocitySetpoint setpoint;
    private double toWrite = 0.0, feedForward = 0.0, feedBack = 0.0, error = 0.0, errorDeriv = 0.0, errorLast = 0.0;
    private int pointer = 0;

    private void calculate(){
        if(localTrajectory != null){
            while (isEnabled) {
                setpoint = localTrajectory[pointer];

                feedForward = setpoint.getVelocity() * kV + setpoint.getAcceleration() * kA;
                toWrite = feedForward; //set to feedforward

                error = setpoint.getDistance() - m_EncoderInstance.getDistance();
                errorDeriv = ((error - errorLast) / nominalDt) - setpoint.getVelocity();
                feedBack = kP * error + kD * errorDeriv;
                toWrite += feedBack; //add feedback

                m_PIDOutputInstance.pidWrite(toWrite);
                errorLast = error;
                pointer++;
                HardwareTimer.hardwareSleep(nominalDt);
            }
        }
    }

    private Runnable runnable = () -> {
        while (keepRunning){
            pointer = 0;
            calculate();
        }
    };
}
