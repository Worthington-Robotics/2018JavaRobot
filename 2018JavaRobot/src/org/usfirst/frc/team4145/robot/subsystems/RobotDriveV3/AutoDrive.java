package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team4145.robot.*;
import org.usfirst.frc.team4145.robot.shared.PidStuff.*;

public class AutoDrive implements DriveUpdater{

    //data objects / fields
    private CustomVelocityPid m_LeftVelocityPID;
    private CustomVelocityPid m_RightVelocityPID;

    //Shared PID Constants
    private double kV = 0.0402; //proportional scalar between motor power level and velocity output Nominal: 0.0402
    private double kA = 0.1000; //proportional scalar between motor power level and acceleration output Nominal:  0.0500
    private double offset = 0.2350; //account for deadband nominal: 0.2350

    //Left PID constants
    private double LEFT_kP = 0.0000; //0.9900
    private double LEFT_kI = 0.0000;
    private double LEFT_kD = 0.0000;

    //Right PID constants
    private double RIGHT_kP = 0.0000; //0.9900
    private double RIGHT_kI = 0.0000;
    private double RIGHT_kD = 0.0000;


    AutoDrive() {
        m_LeftVelocityPID = new CustomVelocityPid(LEFT_kP, LEFT_kI, LEFT_kD, kV, kA, RobotMap.leftWheelEncoder, null, Constants.DRIVETRAIN_UPDATE_RATE, offset);
        m_RightVelocityPID = new CustomVelocityPid(RIGHT_kP, RIGHT_kI, RIGHT_kD, kV, kA, RobotMap.rightWheelEncoder, null, Constants.DRIVETRAIN_UPDATE_RATE, offset);

    }

    public double[] update() {
        SmartDashboard.putBooleanArray("Pids finished" , new boolean[]{m_LeftVelocityPID.isFinished(), m_RightVelocityPID.isFinished()});
        return new double[] {m_LeftVelocityPID.getResult(), m_RightVelocityPID.getResult()};
    }

    public void enableToProfile(Trajectory leftTrajectory, Trajectory rightTrajectory, boolean enableOnNextCycle) {
        System.out.println("PID called to enable on trajectories: " + leftTrajectory + " " + rightTrajectory);
        setProfile(leftTrajectory, rightTrajectory);
        m_LeftVelocityPID.enable(enableOnNextCycle);
        m_RightVelocityPID.enable(enableOnNextCycle);
    }
    private void setProfile(Trajectory leftTrajectory, Trajectory rightTrajectory) {
        m_LeftVelocityPID.setProfile(leftTrajectory);
        m_RightVelocityPID.setProfile(rightTrajectory);
    }

}
