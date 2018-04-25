package org.usfirst.frc.team4145.robot.shared.PidStuff;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

public class CustomVelocityPid {

    private static int instances = 0;
    private final double kP, kI, kD, kV, kA, nominalDt;
    private WPI_TalonSRX m_SRXInstance;
    private Trajectory m_Trajectory;
    private Notifier m_Notifier;
    private boolean isEnabled = false, isFinished = false;
    private Trajectory.Segment setpoint;
    private double toWrite = 0.0, feedForward = 0.0, feedBack = 0.0, error = 0.0, errorDeriv = 0.0, errorLast = 0.0;
    private int index = 0;
    private int instanceNum;
    private double offset;

    private Runnable runnable = () -> calculate();

    public CustomVelocityPid(double kP, double kI, double kD, double kV, double kA, WPI_TalonSRX srx, Trajectory trajectory, double timing, double offset) {
        m_SRXInstance = srx;
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

    public void enable(boolean enable) {
        isEnabled = enable;
        if (isEnabled) {
            index = 0;
            isFinished = false;
            m_Notifier.startPeriodic(nominalDt);
            return;
        }
        //SmartDashboard.putNumber("File length", m_Trajectory.length());
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

    public double getHeading() {
        if (setpoint != null) {
            return setpoint.heading;
        }
        return 0.0;
    }


    private void calculate() {
        if (isEnabled && m_Trajectory != null) {
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
                error = setpoint.position - (m_SRXInstance.getSensorCollection().getQuadraturePosition() / 2607); //2607 counts per foot
                errorDeriv = ((error - errorLast) / nominalDt) - setpoint.velocity;
                feedBack = kP * error + kD * errorDeriv;
                toWrite += feedBack;

                SmartDashboard.putNumber("error deriv" + instanceNum, errorDeriv);
                SmartDashboard.putNumber("setpoint" + instanceNum, setpoint.position * 228);
                SmartDashboard.putNumber("velocity setpoint" + instanceNum, setpoint.velocity);
                SmartDashboard.putNumber("feed forward" + instanceNum, feedForward);
                SmartDashboard.putNumber("feed back" + instanceNum, feedBack);
                SmartDashboard.putNumber("velocity" + instanceNum, toWrite);

                //General cleanup for next iteration
                errorLast = error;
                index++;
                isFinished = false;

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