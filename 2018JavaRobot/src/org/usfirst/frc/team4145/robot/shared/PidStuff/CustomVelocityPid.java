package org.usfirst.frc.team4145.robot.shared.PidStuff;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

public class CustomVelocityPid {

    private static int instances = 0;
    private Encoder m_EncoderInstance;
    private Trajectory m_Trajectory;
    private Notifier m_Notifier;
    private final double kP, kI, kD, kV, kA, nominalDt;
    private boolean isEnabled = false, isFinished = false;
    private Trajectory.Segment setpoint;
    private double toWrite = 0.0, feedForward = 0.0, feedBack = 0.0, error = 0.0, errorDeriv = 0.0, errorLast = 0.0;
    private int index = 0;
    private int instanceNum;
    private double offset;

    private Runnable runnable = () -> calculate();

    public CustomVelocityPid(double kP, double kI, double kD, double kV, double kA, Encoder encoder, Trajectory trajectory, double timing, double offset) {
        m_EncoderInstance = encoder;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kV = kV;
        this.kA = kA;
        this.offset = offset;
        nominalDt = timing;
        m_Trajectory = trajectory;
        m_Notifier = new Notifier(runnable);
        instances++;
        instanceNum = instances;
    }

    public static int getInstances() {
        return instances;
    }

    public void enable(boolean enable){
        isEnabled = enable;
        if (isEnabled) {
            index = 0;
            m_Notifier.startPeriodic(nominalDt);
            return;
        }
        SmartDashboard.putNumber("File length", m_Trajectory.length());
        m_Notifier.stop();
    }

    public void setProfile(Trajectory trajectory) {
        m_Trajectory = trajectory;
    }

    public double getResult() {
        return toWrite;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getInstanceNum() {
        return instanceNum;
    }

    private void calculate() {
        if (m_Trajectory != null) {
            if (isEnabled) {
                if ((index / 2) < m_Trajectory.length()) {
                    //System.out.println("FPGA TIME AT CALC" + instanceNum + ": " + RobotController.getFPGATime());
                    //Every other iteration the feedForward runs
                    if (index % 2 == 0) {
                        //Calculate feed forward part of output
                        setpoint = m_Trajectory.get(index / 2);
                        feedForward = setpoint.velocity * kV + setpoint.acceleration * kA;
                        if (feedForward > 0) {
                            feedForward += offset;
                        }
                        if (feedForward < 0) {
                            feedForward -= offset;
                        }
                    }
                    toWrite = feedForward;

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

                } else {
                    toWrite = 0;
                    isFinished = true;
                }
            } else {
                index = 0;
                toWrite = 0;
                isFinished = false;
            }
        
        }
    }
}
/*
public synchronized double update(MotionState latest_state, double t) {
        mLatestActualState = latest_state;
        MotionState prev_state = latest_state;
        if (mLatestSetpoint != null) {
            prev_state = mLatestSetpoint.motion_state;
        } else {
            mInitialState = prev_state;
        }
        final double dt = Math.max(0.0, t - prev_state.t());
        mLatestSetpoint = mSetpointGenerator.getSetpoint(mConstraints, mGoal, prev_state, t);

        // Update error.
        mLatestPosError = mLatestSetpoint.motion_state.pos() - latest_state.pos();
        mLatestVelError = mLatestSetpoint.motion_state.vel() - latest_state.vel();

        // Calculate the feedforward and proportional terms.
        double output = mKp * mLatestPosError + mKv * mLatestVelError + mKffv * mLatestSetpoint.motion_state.vel() + (Double.isNaN(mLatestSetpoint.motion_state.acc()) ? 0.0 : mKffa * mLatestSetpoint.motion_state.acc());
        if (output >= mMinOutput && output <= mMaxOutput) {
            // Update integral.
            mTotalError += mLatestPosError * dt;
            output += mKi * mTotalError;
        } else {
            // Reset integral windup.
            mTotalError = 0.0;
        }
        // Clamp to limits.
        output = Math.max(mMinOutput, Math.min(mMaxOutput, output));

        return output;
    }
 */