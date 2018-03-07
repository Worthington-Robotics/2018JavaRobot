package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.shared.MixedDrive;
import org.usfirst.frc.team4145.robot.shared.PidStuff.CustomVelocityPid;
import org.usfirst.frc.team4145.robot.shared.PidStuff.VelocitySetpoint;

public class AutoDrive {

    //data objects / fields
    private CustomVelocityPid leftFrontVelocity;
    private CustomVelocityPid leftRearVelocity;
    private CustomVelocityPid rightFrontVelocity;
    private CustomVelocityPid rightRearVelocity;
    private MixedDrive m_MixedDriveInstance;

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


    AutoDrive(MixedDrive mixedDriveInstance){
        m_MixedDriveInstance = mixedDriveInstance;
    }

    public void setProfile(VelocitySetpoint[] rightTrajectory, VelocitySetpoint[] leftTrajectory){
        leftFrontVelocity = new CustomVelocityPid(LEFT_kP, LEFT_kI, LEFT_kD, kV, kA, RobotMap.leftWheelEncoder, RobotMap.driveFrontLeft, leftTrajectory);
        leftRearVelocity = new CustomVelocityPid(LEFT_kP, LEFT_kI, LEFT_kD, kV, kA, RobotMap.leftWheelEncoder, RobotMap.driveRearLeft, leftTrajectory);
        rightFrontVelocity = new CustomVelocityPid(RIGHT_kP, RIGHT_kI, RIGHT_kD, kV, kA, RobotMap.rightWheelEncoder, RobotMap.driveFrontRight, rightTrajectory);
        rightRearVelocity = new CustomVelocityPid(RIGHT_kP, RIGHT_kI, RIGHT_kD, kV, kA, RobotMap.rightWheelEncoder, RobotMap.driveRearRight, rightTrajectory);
    }

    public void enableToProfile(VelocitySetpoint[] rightTrajectory, VelocitySetpoint[] leftTrajectory, boolean enable){
        setProfile(rightTrajectory, leftTrajectory);
        leftFrontVelocity.enable(enable);
        leftRearVelocity.enable(enable);
        rightFrontVelocity.enable(enable);
        rightRearVelocity.enable(enable);

    }

    public double[] update(){
        return null;
    }

}
