package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team4145.robot.*;
import org.usfirst.frc.team4145.robot.shared.PidStuff.*;

public class AutoDrive implements DriveUpdater{

    //data objects / fields
    private CustomVelocityPid m_LeftVelocityPID;
    private CustomVelocityPid m_RightVelocityPID;

    //Shared PID Constants
    private double kV = 0.0000; //proportional scalar between motor power level and velocity output
    private double kA = 0.0000; //proportional scalar between motor power level and acceleration output

    //Left PID constants
    private double LEFT_kP = 0.0000;
    private double LEFT_kI = 0.0000;
    private double LEFT_kD = 0.0000;

    //Right PID constants
    private double RIGHT_kP = 0.0000;
    private double RIGHT_kI = 0.0000;
    private double RIGHT_kD = 0.0000;


    AutoDrive() {

    }

    public double[] update() {
        return new double[] {m_LeftVelocityPID.getResult(), m_RightVelocityPID.getResult()};
    }

    public void enableToProfile(Trajectory rightTrajectory, Trajectory leftTrajectory, boolean enableOnNextCycle) {
        setProfile(rightTrajectory, leftTrajectory);
        m_LeftVelocityPID.enable(enableOnNextCycle);
        m_RightVelocityPID.enable(enableOnNextCycle);
    }
    private void setProfile(Trajectory rightTrajectory, Trajectory leftTrajectory) {
        m_LeftVelocityPID = new CustomVelocityPid(LEFT_kP, LEFT_kI, LEFT_kD, kV, kA, RobotMap.leftWheelEncoder, leftTrajectory, Constants.DRIVETRAIN_UPDATE_RATE);
        m_RightVelocityPID = new CustomVelocityPid(RIGHT_kP, RIGHT_kI, RIGHT_kD, kV, kA, RobotMap.rightWheelEncoder, rightTrajectory, Constants.DRIVETRAIN_UPDATE_RATE);
    }

}
