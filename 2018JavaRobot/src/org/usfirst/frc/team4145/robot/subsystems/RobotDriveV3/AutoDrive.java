package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team4145.robot.*;
import org.usfirst.frc.team4145.robot.shared.PidStuff.*;

public class AutoDrive implements DriveUpdater{

    //data objects / fields
    private CustomVelocityPid m_LeftVelocityPID;
    private CustomVelocityPid m_RightVelocityPID;
    private boolean isProfiling = false;
    private double angleDiff = 0, turnComp = 0, lastError = 0;
    public double[] toWrite = {0, 0};

    //Shared PID Constants

    private double offset = Constants.getOffset(); //account for deadband
    private double kP_Turn = Constants.getGyroKp(); //gyro correction strength
    private double kD_Turn = 0.0000;

    //Left PID constants
    private double LEFT_kV = Constants.getLeftKV(); //proportional scalar between motor power level and velocity output
    private double LEFT_kA = Constants.getLeftKA(); //proportional scalar between motor power level and acceleration output
    private double LEFT_kP = Constants.getLeftKP();
    private double LEFT_kI = 0.0000;
    private double LEFT_kD = Constants.getLeftKD();

    //Right PID constants
    private double RIGHT_kV = Constants.getRightKV(); //proportional scalar between motor power level and velocity output
    private double RIGHT_kA = Constants.getRightKA(); //proportional scalar between motor power level and acceleration output
    private double RIGHT_kP = Constants.getRightKP();
    private double RIGHT_kI = 0.0000;
    private double RIGHT_kD = Constants.getRightKD();



    AutoDrive() {
        m_LeftVelocityPID = new CustomVelocityPid(LEFT_kP, LEFT_kI, LEFT_kD, LEFT_kV, LEFT_kA, RobotMap.driveFrontLeft, null, Constants.DRIVETRAIN_UPDATE_RATE, offset);
        m_RightVelocityPID = new CustomVelocityPid(RIGHT_kP, RIGHT_kI, RIGHT_kD, RIGHT_kV, RIGHT_kA, RobotMap.driveFrontRight, null, Constants.DRIVETRAIN_UPDATE_RATE, offset);

    }

    public double[] update() {
        toWrite[0] = m_LeftVelocityPID.getResult();
        toWrite[1] = m_RightVelocityPID.getResult();
        angleDiff = Pathfinder.boundHalfDegrees(getHeading() - getGyro());
        turnComp = kP_Turn * (-1.0/80.0) * angleDiff + kD_Turn* ((angleDiff - lastError) / Constants.DRIVETRAIN_UPDATE_RATE);
        toWrite[0] += turnComp;
        toWrite[1] -= turnComp;
        lastError = angleDiff;
        SmartDashboard.putBooleanArray("Pids finished" , new boolean[]{m_LeftVelocityPID.isFinished(), m_RightVelocityPID.isFinished()});
        SmartDashboard.putBoolean("isProfiling", isProfiling);
        SmartDashboard.putNumber("Turn Compensation", turnComp);
        return toWrite;
    }

    public double getGyro() {
        return ((RobotMap.ahrs.getYaw() + 360) % 360); //add 360 to make all positive then mod by 360 to get remainder
    }

    public boolean isProfiling(){
        return isProfiling && (!m_LeftVelocityPID.isFinished() || !m_RightVelocityPID.isFinished());
    }

    public boolean isFinished(){
        return m_LeftVelocityPID.isFinished() && m_LeftVelocityPID.isFinished();
    }

    public double getHeading(){
        return Pathfinder.r2d(m_LeftVelocityPID.getHeading());
    }

    public void enableToProfile(Trajectory leftTrajectory, Trajectory rightTrajectory, boolean enableOnNextCycle) {
        isProfiling = enableOnNextCycle;
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
