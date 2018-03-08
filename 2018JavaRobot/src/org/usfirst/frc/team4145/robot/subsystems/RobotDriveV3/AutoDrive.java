package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import org.usfirst.frc.team4145.robot.Robot;
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

    //general use variables
    private double DEADBAND_VALUE = 0.15; //nominal deadband 0.15 percent of stick
    private double Y_PERCENTAGE = 0.75; // decrease xy output to percent of full Nominal: 0.75
    private double Y_CUT_PERCENTAGE = 0.5; //fine adjust Y Nominal: 0.5
    private double X_PERCENTAGE = 1.0; //decrease X to percent of full Nominal: 1.0
    private double X_CUT_PERCENTAGE = 1.0; //fine adjust X Nominal: 1.0
    private double Z_PERCENTAGE = 0.50; // z percentage of full stick deflection Nominal: 0.50

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

    public void setProfile(VelocitySetpoint[] rightTrajectory, VelocitySetpoint[] leftTrajectory) {
        leftFrontVelocity = new CustomVelocityPid(LEFT_kP, LEFT_kI, LEFT_kD, kV, kA, RobotMap.leftWheelEncoder, RobotMap.driveFrontLeft, leftTrajectory);
        rightFrontVelocity = new CustomVelocityPid(RIGHT_kP, RIGHT_kI, RIGHT_kD, kV, kA, RobotMap.rightWheelEncoder, RobotMap.driveFrontRight, rightTrajectory);
    }

    public void enableToProfile(VelocitySetpoint[] rightTrajectory, VelocitySetpoint[] leftTrajectory, boolean enable){
        setProfile(rightTrajectory, leftTrajectory);
        leftFrontVelocity.enable(enable);
        leftRearVelocity.enable(enable);
        rightFrontVelocity.enable(enable);
        rightRearVelocity.enable(enable);

    }

    public double[] update(){
        //return getAdjStick();
        return null;
    }

    private double[] getAdjStick() {
        double[] out = new double[3];
        out[0] = evalDeadBand(Robot.oi.getMasterStick().getY(), DEADBAND_VALUE) * Y_PERCENTAGE;
        out[1] = evalDeadBand(Robot.oi.getMasterStick().getX(), DEADBAND_VALUE) * X_PERCENTAGE;
        out[2] = evalDeadBand(Robot.oi.getMasterStick().getZ(), DEADBAND_VALUE) * Z_PERCENTAGE;
        return out;
    }

    // figures out if the stick value is within the deadband
    private double evalDeadBand(double stickInpt, double deadBand) {
        if (Math.abs(stickInpt) < deadBand) {
            return 0;
        } else {
            if (stickInpt < 0) {
                return (0 - Math.pow(stickInpt, 2));
            } else {
                return Math.pow(stickInpt, 2);
            }
        }
    }

}
