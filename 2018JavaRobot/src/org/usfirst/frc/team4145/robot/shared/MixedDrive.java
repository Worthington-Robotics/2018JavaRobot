package org.usfirst.frc.team4145.robot.shared;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MixedDrive extends MecanumDrive{

    private WPI_TalonSRX kFrontLeft;
    private WPI_TalonSRX kFrontRight;
    private WPI_TalonSRX kRearLeft;
    private WPI_TalonSRX kRearRight;
    private int PID_IDX;
    
    public enum DriveSelector{
        FRONT_LEFT, FRONT_RIGHT, REAR_LEFT, REAR_RIGHT
    }

    /**
     * mixed meccanum drivetrain constructor with a pid slot parameter
     * @param frontLeft talon srx motor controller for front left
     * @param rearLeft talon srx motor controller for rear left
     * @param frontRight talon srx motor controller for front right
     * @param rearRight talon srx motor controller for rear right
     * @param pidIDSlot pid id# use with multiple PIDS
     */
    public MixedDrive(WPI_TalonSRX frontLeft, WPI_TalonSRX rearLeft, WPI_TalonSRX frontRight, WPI_TalonSRX rearRight, int pidIDSlot){
        super(frontLeft, rearLeft, frontRight, rearRight);
        PID_IDX = pidIDSlot;
        kFrontLeft = frontLeft;
        kFrontRight = frontRight;
        kRearLeft = rearLeft;
        kRearRight = rearRight;
        setName("Mecanum Differential Drive");
    }

    /**
     * mixed meccanum drivetrain constructor with a pid slot parameter set to zero
     * @param frontLeft talon srx motor controller for front left
     * @param rearLeft talon srx motor controller for rear left
     * @param frontRight talon srx motor controller for front right
     * @param rearRight talon srx motor controller for rear right
     */
    public MixedDrive(WPI_TalonSRX frontLeft, WPI_TalonSRX rearLeft, WPI_TalonSRX frontRight, WPI_TalonSRX rearRight){
       this(frontLeft,rearLeft,frontRight,rearRight,0);
    }

    /**
     * use to configure multiple PID slots
     * @param PID_IDX pid ID number (0 - 4)
     */
    public void setPID_IDX(int PID_IDX){
        this.PID_IDX = PID_IDX;
    }

    /**
     * method for configuring robotDriveV4 talons in master mode
     * @param selector talon to configure for velocity PID control
     * @param kP proportional gain
     * @param kI integral gain
     * @param kD derivative gain
     * @param kF feed forward gain
     */
    public void configureVelocityPIDF(DriveSelector selector, double kP, double kI, double kD, double kF){
        switch (selector){
            case FRONT_LEFT:
                kFrontLeft.set(ControlMode.Velocity, 0);
                kFrontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
                kFrontLeft.setSensorPhase(true);
                kFrontLeft.selectProfileSlot(0, PID_IDX);
                kFrontLeft.config_kF(0, kF, 0);
                kFrontLeft.config_kP(0, kP, 0);
                kFrontLeft.config_kI(0, kI, 0);
                kFrontLeft.config_kD(0, kD, 0);
                kFrontLeft.config_IntegralZone(0, 0, 0);
                return;
            case FRONT_RIGHT:
                kFrontRight.set(ControlMode.Velocity, 0);
                kFrontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
                kFrontRight.setSensorPhase(true);
                kFrontRight.selectProfileSlot(0, PID_IDX);
                kFrontRight.config_kF(0, kF, 0);
                kFrontRight.config_kP(0, kP, 0);
                kFrontRight.config_kI(0, kI, 0);
                kFrontRight.config_kD(0, kD, 0);
                kFrontRight.config_IntegralZone(0, 0, 0);
                return;
            case REAR_LEFT:
                kRearLeft.set(ControlMode.Velocity, 0);
                kRearLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
                kRearLeft.setSensorPhase(true);
                kRearLeft.selectProfileSlot(0, PID_IDX);
                kRearLeft.config_kF(0, kF, 0);
                kRearLeft.config_kP(0, kP, 0);
                kRearLeft.config_kI(0, kI, 0);
                kRearLeft.config_kD(0, kD, 0);
                kRearLeft.config_IntegralZone(0, 0, 0);
                return;
            case REAR_RIGHT:
                kRearRight.set(ControlMode.Velocity, 0);
                kRearRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
                kRearRight.setSensorPhase(true);
                kRearRight.selectProfileSlot(0, PID_IDX);
                kRearRight.config_kF(0, kF, 0);
                kRearRight.config_kP(0, kP, 0);
                kRearRight.config_kI(0, kI, 0);
                kRearRight.config_kD(0, kD, 0);
                kRearRight.config_IntegralZone(0, 0, 0);
                return;
        }
    }

    /**
     * resets the selected sensor for the system PID ID
     */
    public void resetSelectedSensors(){
        kFrontLeft.setSelectedSensorPosition(0,PID_IDX,0);
        kRearLeft.setSelectedSensorPosition(0,PID_IDX,0);
        kFrontRight.setSelectedSensorPosition(0,PID_IDX,0);
        kRearRight.setSelectedSensorPosition(0,PID_IDX,0);
    }

    /**
     * Sets brake mode dynamically across all talons
     * @param brakeFL enable front left brake
     * @param brakeRL enable rear left brake
     * @param brakeFR enable front right brake
     * @param brakeRR enable rear right brake
     */
    public void setDynamicBrakeMode(boolean brakeFL, boolean brakeRL, boolean brakeFR, boolean brakeRR) {
        kFrontLeft.setNeutralMode(brakeFL? NeutralMode.Brake : NeutralMode.Coast);
        kRearLeft.setNeutralMode(brakeRL? NeutralMode.Brake : NeutralMode.Coast);
        kFrontRight.setNeutralMode(brakeFR? NeutralMode.Brake : NeutralMode.Coast);
        kRearRight.setNeutralMode(brakeRR? NeutralMode.Brake : NeutralMode.Coast);
    }

    /**
     * uses preconfigured velocity control mode and follower mode on rear talons
     * @param leftSpeed ticks per 100ms
     * @param rightSpeed ticks per 100ms
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        SmartDashboard.putNumberArray("Internal tank values",new double[] {leftSpeed, rightSpeed});
        kFrontLeft.set(ControlMode.Velocity, leftSpeed);
        kFrontRight.set(ControlMode.Velocity, -rightSpeed);
        kRearLeft.set(ControlMode.Follower, kFrontLeft.getDeviceID());
        kRearRight.set(ControlMode.Follower, kFrontRight.getDeviceID());
        m_safetyHelper.feed();
    }

}
