package org.usfirst.frc.team4145.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.shared.MixedDrive;

public class RobotDriveV3 extends Subsystem {

    private MixedDrive m_MixedDriveInstance;

    public RobotDriveV3(){
        m_MixedDriveInstance = new MixedDrive(RobotMap.driveFrontLeft, RobotMap.driveRearLeft, RobotMap.driveFrontRight, RobotMap.driveRearRight);
    }

    @Override
    protected void initDefaultCommand() {

    }


    public void periodic(){
        if(!DriverStation.getInstance().isAutonomous()){

        }
    }

    public void setNeutralMode(boolean brake){
        if(brake){
            setDynamicBrakeMode(true, true, true, true);
        }
        else{
            setDynamicBrakeMode(false, false, false, false);
        }
    }
    public void setDynamicBrakeMode(boolean brakeFL, boolean brakeRL, boolean brakeFR, boolean brakeRR){
        if(brakeFL){
            RobotMap.driveFrontLeft.setNeutralMode(NeutralMode.Brake);
        }
        else{
            RobotMap.driveFrontLeft.setNeutralMode(NeutralMode.Coast);
        }
        if(brakeRL){
            RobotMap.driveRearLeft.setNeutralMode(NeutralMode.Brake);
        }
        else{
            RobotMap.driveRearLeft.setNeutralMode(NeutralMode.Coast);
        }
        if(brakeFR){
            RobotMap.driveFrontRight.setNeutralMode(NeutralMode.Brake);
        }
        else{
            RobotMap.driveFrontRight.setNeutralMode(NeutralMode.Coast);
        }
        if(brakeRR){
            RobotMap.driveRearRight.setNeutralMode(NeutralMode.Brake);
        }
        else{
            RobotMap.driveRearRight.setNeutralMode(NeutralMode.Coast);
        }
    }

    /**
     * Method for exposing the underlying MixedDrive object to mecanum control
     * @param ySpeed  forward / backward speed of the robot
     * @param xSpeed  left / right speed of the robot
     * @param zRotation rotational speed of the robot
     */
    private void driveCartesian(double ySpeed, double xSpeed, double zRotation){
        m_MixedDriveInstance.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    /**
     * Method for exposing the underlying MixedDrive object for tank control
     * @param leftSpeed  speed of left side of drivetrain
     * @param rightSpeed  speed of right side of drivetrain
     */
    private void driveTank(double leftSpeed, double rightSpeed){
        m_MixedDriveInstance.tankDrive(leftSpeed, rightSpeed, false);
    }



}
