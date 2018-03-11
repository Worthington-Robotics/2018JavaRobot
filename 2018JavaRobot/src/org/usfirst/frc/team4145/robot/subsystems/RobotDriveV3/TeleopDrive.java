package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;

public class TeleopDrive extends RobotDriveMode{

    //used internally for data
    private PIDController gyroLock;
    private double pidOutput = 0; //DO NOT MODIFY
    private boolean enLock = false;
    private boolean isReversed = false;
    private boolean isLowGear = false;
    private double[] lastInputSet = {0, 0, 0}; //last input set from joystick update

    //general use variables
    private double DEADBAND_VALUE = 0.15; //nominal deadband 0.15 percent of stick
    private double Y_PERCENTAGE = 0.75; // decrease xy output to percent of full Nominal: 0.75
    private double Y_CUT_PERCENTAGE = 0.5; //fine adjust Y Nominal: 0.5
    private double X_PERCENTAGE = 1.0; //decrease X to percent of full Nominal: 1.0
    private double X_CUT_PERCENTAGE = 1.0; //fine adjust X Nominal: 1.0
    private double Z_PERCENTAGE = 0.50; // z percentage of full stick deflection Nominal: 0.50

    //PID variables
    private double PROPORTIONAL_GAIN = 0.033; //stable at 0.033
    private double INTEGRAL_GAIN = 0.0; //dont generally use Integral as it makes things unstable over time
    private double DERIVATIVE_GAIN = 0.055; //stable at 0.045
    private double ABSOLUTE_TOLERANCE = 1.0; //tolerance on PID control Nominal: 0.5
    private double PID_LIMIT = 0.65; //limits pid output Nominal: 0.6

    TeleopDrive(){
        gyroLock = new PIDController(PROPORTIONAL_GAIN, INTEGRAL_GAIN, DERIVATIVE_GAIN, this, this);
        gyroLock.setAbsoluteTolerance(ABSOLUTE_TOLERANCE);
        gyroLock.setOutputRange(-PID_LIMIT, PID_LIMIT);
        gyroLock.setInputRange(0, 360);
        gyroLock.setContinuous();
    }

    public double[] update(){
        lastInputSet = getAdjStick();
        if (isReversed) {
            lastInputSet[0] *= -1;
            lastInputSet[1] *= -1;
        }
        evalLowGear();
        if (isLowGear) {
            lastInputSet[0] *= Y_CUT_PERCENTAGE;
            lastInputSet[1] *= X_CUT_PERCENTAGE;
        }
        if (enLock) {
            // Periodically updates while gyro locked
           lastInputSet[2] = pidOutput;

        } else {
            // periodically updates drive
            setTarget(getGyro()); // Safety feature in case PID gets enabled
        }
        smartDashboardUpdates();
        //lastInputSet[0] = -0.6; lastInputSet[1] = 0; lastInputSet[2] = 0; //FOR TESTING PURPOSES
        return lastInputSet;
    }

    public void enableTo(double rot, boolean en) {
        this.setTarget(rot);
        this.enableLock(en);
    }

    private void setTarget(double target) {
        gyroLock.setSetpoint(target);
    }

    private void enableLock(boolean en) {
        enLock = en;
        if (enLock) gyroLock.enable();
        else gyroLock.disable();
    }

    public double pidGet(){
        return getGyro();
    }
    public void pidWrite(double output){
        pidOutput = output;
    }

    public double getGyro() {
        return ((RobotMap.ahrs.getYaw() + 360) % 360); //add 360 to make all positive then mod by 360 to get remainder
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

    private void evalLowGear() {
        isLowGear = Robot.oi.getMasterStick().getPOV() >= 0;
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

    private void smartDashboardUpdates(){
        SmartDashboard.putNumberArray("compensated stick values", lastInputSet);
        SmartDashboard.putNumber("Gyro Target", gyroLock.getSetpoint());
        SmartDashboard.putNumber("Gyro Angle", getGyro());
        SmartDashboard.putBoolean("Gyro lock enabled", enLock);
    }
}
