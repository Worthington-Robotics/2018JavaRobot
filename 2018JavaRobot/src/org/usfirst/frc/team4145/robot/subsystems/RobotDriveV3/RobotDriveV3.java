package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.shared.MixedDrive;

public class RobotDriveV3 extends Subsystem {

    //used internally for data
    private MixedDrive m_MixedDriveInstance;
    private TeleopDrive m_TeleopDriveInstance;
    private AutoDrive m_AutoDriveInstance;
    private Notifier m_NotifierInstance;

    private double[] lastTeleopOutput = {0,0}; //y, x
    private double[] lastAutoOutput = {0,0,0}; //left, right, turn (not used by profiling)

    public RobotDriveV3() {
        m_MixedDriveInstance = new MixedDrive(RobotMap.driveFrontLeft, RobotMap.driveRearLeft, RobotMap.driveFrontRight, RobotMap.driveRearRight);
        m_TeleopDriveInstance = new TeleopDrive();
        m_AutoDriveInstance = new AutoDrive();
        m_NotifierInstance = new Notifier(periodic);
    }

    public static void enableVcomp(TalonSRX srx){
        srx.configVoltageCompSaturation(11.0, 10);
        srx.enableVoltageCompensation(true);
        srx.configVoltageMeasurementFilter(32,10);
    }

    public void startPeriodic(){
        m_NotifierInstance.startPeriodic(Constants.DRIVETRAIN_UPDATE_RATE);
    }

    public TeleopDrive getTeleopDriveInstance() {
        return m_TeleopDriveInstance;
    }

    public AutoDrive getAutoDriveInstance() {
        return m_AutoDriveInstance;
    }

    private Runnable periodic = () -> {
        if (DriverStation.getInstance().isAutonomous() && m_AutoDriveInstance.isProfiling() ) {
            //System.out.println("Profiling mode");
            lastAutoOutput = m_AutoDriveInstance.update();
            SmartDashboard.putNumberArray("Tank Drive Values", lastAutoOutput);
            SmartDashboard.putNumber("Gyro Target", m_AutoDriveInstance.getHeading());
            driveTank(lastAutoOutput[0], lastAutoOutput[1]);

        }
        else if(!DriverStation.getInstance().isTest()){
            //System.out.println("mecanum mode");
            lastTeleopOutput = m_TeleopDriveInstance.update();
            driveCartesian(lastTeleopOutput[1], -lastTeleopOutput[0], lastTeleopOutput[2]);
        }

        smartDashboardUpdates();
    };

    public void setAutoDrive(double[] toSet){
        lastAutoOutput = toSet;
    }

    public double getGyro() {
        return ((RobotMap.ahrs.getYaw() + 360) % 360); //add 360 to make all positive then mod by 360 to get remainder
    }

    public int getLeftEncoder(){
        return RobotMap.driveFrontLeft.getSensorCollection().getQuadraturePosition();
    }

    public int getRightEncoder(){
        return RobotMap.driveFrontRight.getSensorCollection().getQuadraturePosition();
    }

    public void resetEncoders(){
        RobotMap.driveFrontLeft.getSensorCollection().setQuadraturePosition(0,0);
        RobotMap.driveFrontRight.getSensorCollection().setQuadraturePosition(0,0);
    }

    public void resetGyro(){
        RobotMap.ahrs.reset();
    }

    public void reset(){
        resetEncoders();
        resetGyro();
    }

    public void setNeutralMode(boolean brake) {
        if (brake) {
            setDynamicBrakeMode(true, true, true, true);
        } else {
            setDynamicBrakeMode(false, false, false, false);
        }
    }

    public void setDynamicBrakeMode(boolean brakeFL, boolean brakeRL, boolean brakeFR, boolean brakeRR) {
        if (brakeFL) {
            RobotMap.driveFrontLeft.setNeutralMode(NeutralMode.Brake);
        } else {
            RobotMap.driveFrontLeft.setNeutralMode(NeutralMode.Coast);
        }
        if (brakeRL) {
            RobotMap.driveRearLeft.setNeutralMode(NeutralMode.Brake);
        } else {
            RobotMap.driveRearLeft.setNeutralMode(NeutralMode.Coast);
        }
        if (brakeFR) {
            RobotMap.driveFrontRight.setNeutralMode(NeutralMode.Brake);
        } else {
            RobotMap.driveFrontRight.setNeutralMode(NeutralMode.Coast);
        }
        if (brakeRR) {
            RobotMap.driveRearRight.setNeutralMode(NeutralMode.Brake);
        } else {
            RobotMap.driveRearRight.setNeutralMode(NeutralMode.Coast);
        }
    }

    private void smartDashboardUpdates() {
        SmartDashboard.putNumber("Right Wheel Encoder", getRightEncoder());
        SmartDashboard.putNumber("Left Wheel Encoder", getLeftEncoder());
        SmartDashboard.putNumber("FPGA Time", Timer.getFPGATimestamp());
        SmartDashboard.putNumber("Gyro Angle", getGyro());
        SmartDashboard.putNumber("Left Motor Voltage", RobotMap.driveFrontLeft.getMotorOutputVoltage());
        SmartDashboard.putNumber("Right Motor Voltage", RobotMap.driveFrontRight.getMotorOutputVoltage());
        SmartDashboard.putNumber("Left Talon Voltage", RobotMap.driveRearLeft.getBusVoltage());
        SmartDashboard.putNumber("Right Talon Voltage", RobotMap.driveRearRight.getBusVoltage());
    }

    /**
     * Method for exposing the underlying MixedDrive object to mecanum control
     *
     * @param ySpeed    forward / backward speed of the robot
     * @param xSpeed    left / right speed of the robot
     * @param zRotation rotational speed of the robot
     */
    private void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
        m_MixedDriveInstance.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    /**
     * Method for exposing the underlying MixedDrive object for tank control
     *
     * @param leftSpeed  speed of left side of drivetrain
     * @param rightSpeed speed of right side of drivetrain
     */
    private void driveTank(double leftSpeed, double rightSpeed) {
        m_MixedDriveInstance.tankDrive(leftSpeed, rightSpeed, false);
    }

    protected void initDefaultCommand() {

    }

}
