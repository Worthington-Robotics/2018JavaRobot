package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.shared.MixedDrive;

public class RobotDriveV3 {

    private MixedDrive m_MixedDriveInstance;
    private boolean[] dynamicBrake;

    public RobotDriveV3(){
        m_MixedDriveInstance = new MixedDrive(RobotMap.driveFrontLeft, RobotMap.driveRearLeft, RobotMap.driveFrontRight, RobotMap.driveRearRight);
    }

    /**
     * Method for exposing the underlying MixedDrive object to mecanum control
     * @param ySpeed  forward / backward speed of the robot
     * @param xSpeed  left / right speed of the robot
     * @param zRotation rotational speed of the robot
     */
    public void driveCartesian(double ySpeed, double xSpeed, double zRotation){
        m_MixedDriveInstance.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    /**
     * Method for exposing the underlying MixedDrive object for tank control
     * @param leftSpeed
     * @param rightSpeed
     */
    public void driveTank(double leftSpeed, double rightSpeed){
        m_MixedDriveInstance.tankDrive(leftSpeed, rightSpeed, false);
    }



}
