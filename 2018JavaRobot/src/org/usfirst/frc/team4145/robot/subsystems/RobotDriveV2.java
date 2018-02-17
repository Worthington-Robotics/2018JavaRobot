package org.usfirst.frc.team4145.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.Quaternion;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.shared.CustomPIDSubsystem;

/**
 * Robot drive version 2
 * this version of the drive system is built based on 0 - 360 from the ground up
 * if you want the current gyro angle call getGyro()
 */

public class RobotDriveV2 extends CustomPIDSubsystem {

    //used internally for data
    private PIDController gyroLock;
    private double pidOutput = 0; //DO NOT MODIFY
    private boolean enLock = false;
    private boolean isReversed = false;
    private boolean isLowGear = false;
    private double[] lastInputSet = {0, 0, 0}; //last input set from joystick update

    //general use variables
    private boolean BRAKE_MODE = true; //whether to disable or enable brake mode Nominal: true
    private double DEADBAND_VALUE = 0.15; //nominal deadband 0.15 percent of stick
    private double Y_PERCENTAGE = 0.75; // decrease xy output to percent of full Nominal: 0.75
    private double Y_CUT_PERCENTAGE = 0.5; //fine adjust Y Nominal: 0.5
    private double X_PERCENTAGE = 1.0; //decrease X to percent of full Nominal: 1.0
    private double X_CUT_PERCENTAGE = 1.0; //fine adjust X Nominal: 1.0
    private double Z_PERCENTAGE = 0.50; // z percentage of full stick deflection Nominal: 0.50
    private double FRONT_RAMP = 0.0; //ramp time on front motors Nominal: 0.0
    private double REAR_RAMP = 0.0; //ramp time on rear motors Nominal: 0.0

    //PID variables
    private double PROPORTIONAL_GAIN = 0.033; //stable at 0.033
    private double INTEGRAL_GAIN = 0.0; //dont generally use Integral as it makes things unstable over time
    private double DERIVATIVE_GAIN = 0.055; //stable at 0.045
    private double ABSOLUTE_TOLERANCE = 0.5; //tolerance on PID control Nominal: 0.5
    private double PID_LIMIT = 0.75; //limits pid output Nominal: 0.6


    public RobotDriveV2() {
        gyroLock = new PIDController(PROPORTIONAL_GAIN, INTEGRAL_GAIN, DERIVATIVE_GAIN, this, this::pidWrite);
        gyroLock.setAbsoluteTolerance(ABSOLUTE_TOLERANCE);
        gyroLock.setOutputRange(-PID_LIMIT, PID_LIMIT);
        gyroLock.setInputRange(0, 360);
        gyroLock.setContinuous();
        setBRAKE_MODE(BRAKE_MODE);
        setRamp(FRONT_RAMP, REAR_RAMP);
    }

    @Override
    public void initDefaultCommand() {

    }

    @Override
    public double pidGet() {
        return getGyro();
    }

    @Override
    public void pidWrite(double output) {
        pidOutput = output;
    }

    public void periodic() {
        if (!DriverStation.getInstance().isAutonomous()) lastInputSet = getAdjStick();
        if (isReversed) {
            lastInputSet[0] *= -1;
            lastInputSet[1] *= -1;
        }
        evalLowGear();
        if(isLowGear){
            lastInputSet[0] *= Y_CUT_PERCENTAGE;
            lastInputSet[1] *= X_CUT_PERCENTAGE;
        }
        SmartDashboard.putNumberArray("compensated stick values", lastInputSet);
        SmartDashboard.putNumber("Gyro Target", gyroLock.getSetpoint());
        SmartDashboard.putNumber("Gyro Angle", getGyro());
        SmartDashboard.putBoolean("Gyro lock enabled",enLock);
        SmartDashboard.putNumber("FPGA Time" , Timer.getFPGATimestamp());
        if (enLock) {
            // Periodically updates while gyro locked
            setCartesianDrive(lastInputSet[0], lastInputSet[1], pidOutput);

        } else {
            // periodically updates drive
            setCartesianDrive(lastInputSet[0], lastInputSet[1], lastInputSet[2]);
            setTarget(getGyro()); // Safety feature in case PID gets enabled
        }
    }

    /**
     * Enables gyro-lock to a certain setpoint -- used for auto
     *
     * @param rot - rotation angle target
     * @param en  - whether to enable or disable the lock
     */

    public void enableTo(double rot, boolean en) {
        this.setTarget(rot);
        this.enableLock(en);
    }

    public void setInput(double[] inputvals) {
        lastInputSet = inputvals;
    }

    /**
     * method for activating the axial reverse that the drivers use
     * it simply tells the periodic method to multipy x and y by -1
     *
     * @param isReversed whether or not is in a reversed state
     */
    public void flipRefrence(boolean isReversed) {
        this.isReversed = isReversed;
    }

    public void setLowGear(boolean toSet){
        isLowGear = toSet;
    }

    /**
     * method for getting current gyro heading
     *
     * @return current gyro position (0-359.99999)
     */
    public double getGyro() {
        return ((RobotMap.ahrs.getYaw() + 360) % 360); //add 360 to make all positive then mod by 360 to get remainder
    }

    public double getAHRS(){
        return RobotMap.ahrs.getYaw();
    }

    /**
     * modify's the gains on the PID as needed
     *
     * @param p proportional gain
     * @param i integral gain
     * @param d derivative gain
     */
    public void setPid(double p, double i, double d) {
        gyroLock.setPID(p, i, d);
    }

    /**
     * gets the current status of the PID controller
     *
     * @return boolean is enabled
     */
    public boolean getPIDStatus() {
        return gyroLock.isEnabled();
    }

    /**
     * gets whether or not the PID controller is on target
     *
     * @return boolean is on target
     */
    public boolean isOnTarget() {
        return gyroLock.onTarget();
    }

    public void setRamp(double front, double rear) {
        RobotMap.driveMotor1.configOpenloopRamp(front, 10);
        RobotMap.driveMotor2.configOpenloopRamp(rear, 10);
        RobotMap.driveMotor3.configOpenloopRamp(front, 10);
        RobotMap.driveMotor4.configOpenloopRamp(rear, 10);
    }

    public void setBRAKE_MODE(boolean BRAKE_MODE) {
        if (BRAKE_MODE) {
            RobotMap.driveMotor1.setNeutralMode(NeutralMode.Brake);
            RobotMap.driveMotor2.setNeutralMode(NeutralMode.Brake);
            RobotMap.driveMotor3.setNeutralMode(NeutralMode.Brake);
            RobotMap.driveMotor4.setNeutralMode(NeutralMode.Brake);
        }
        RobotMap.driveMotor1.setNeutralMode(NeutralMode.Coast);
        RobotMap.driveMotor2.setNeutralMode(NeutralMode.Coast);
        RobotMap.driveMotor3.setNeutralMode(NeutralMode.Coast);
        RobotMap.driveMotor4.setNeutralMode(NeutralMode.Coast);
    }

    //private methods here
    private void setCartesianDrive(double x, double y, double z) {
        RobotMap.robotdrive.driveCartesian(y, -x, z);
    }

    private void evalLowGear() {
        if(Robot.oi.getMasterStick().getPOV() >= 0) {
        	isLowGear = true;
        } else {
        	isLowGear = false;
        }
    }
    
    private void setTarget(double target) {
        gyroLock.setSetpoint(target);
    }

    private void enableLock(boolean en) {
        enLock = en;
        if (enLock) gyroLock.enable();
        else gyroLock.disable();
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

    private Quaternion getQuarternion(){
        float w = RobotMap.ahrs.getQuaternionW();
        float x = RobotMap.ahrs.getQuaternionX();
        float y  = RobotMap.ahrs.getQuaternionY();
        float z = RobotMap.ahrs.getQuaternionZ();
        return new Quaternion(w, x, y, z);
    }

}
