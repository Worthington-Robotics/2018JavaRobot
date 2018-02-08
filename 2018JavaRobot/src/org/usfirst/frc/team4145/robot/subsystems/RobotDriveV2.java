package org.usfirst.frc.team4145.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.team4145.robot.shared.AccessiblePIDOutput;
import org.usfirst.team4145.robot.shared.CustomPIDSubsystem;

/**
 * Robot drive version 2
 * this version of the drive system is built based on 0 - 360 from the ground up
 * if you want the current gyro angle call getGyro()
 */

public class RobotDriveV2 extends CustomPIDSubsystem {

    private PIDController gyroLock;
    private AccessiblePIDOutput output;

    private boolean enLock = false;
    private double deadBandVal = 0.35;
    private double[] lastInputSet = {0, 0, 0};
    private boolean isReversed = false;

    //PID variables
    private double Kp = 0.025;
    private double Ki = 0.0;
    private double Kd = 0.025;
    private double absTol = 0;
    private double pidOutput = 0;

    public RobotDriveV2() {
        output = new AccessiblePIDOutput();
        gyroLock = new PIDController(Kp, Ki, Kd, this, this::pidWrite);
        gyroLock.setAbsoluteTolerance(absTol);
        gyroLock.setOutputRange(-1,1);
        //gyroLock.setContinuous();

    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public double pidGet() {
        return getGyro();
    }

    @Override
    public void pidWrite(double output) {
        SmartDashboard.putNumber("PIDWrite", output);
        pidOutput = output;
        pidOutput = gyroLock.get();
    }

    public void periodic() {
        if (!DriverStation.getInstance().isAutonomous()) lastInputSet = getAdjStick();
        if (isReversed) {
            lastInputSet[0] *= -1;
            lastInputSet[1] *= -1;
        }
        SmartDashboard.putNumberArray("compensated stick values", lastInputSet);
        SmartDashboard.putNumber("Gyro Target", gyroLock.getSetpoint());
        SmartDashboard.putNumber("Gyro Angle", getGyro());
        SmartDashboard.putNumber("Pid error", gyroLock.getError());
        SmartDashboard.putNumber("PID Output",output.getValue());
        SmartDashboard.putBoolean("IS enabled", gyroLock.isEnabled());
        if (enLock) {
            // Periodically updates while gyro locked

            setCartesianDrive(lastInputSet[0], lastInputSet[1], output.getValue());

        } else {
            // periodically updates drive
            setCartesianDrive(lastInputSet[0], lastInputSet[1], lastInputSet[2] / 1.25);
            setTarget(getGyro()); // Safety feature in case PID gets enabled
        }
    }

    public void enableTo(double rot, boolean en) {
        this.setTarget(rot);
        this.enableLock(en);
    }

    public void setInput(double[] inputvals) {
        lastInputSet = inputvals;
    }

    public void flipRefrence(boolean isReversed) {
        this.isReversed = isReversed;
    }

    public double getGyro() {
        return Math.abs(RobotMap.ahrs.getAngle() % 360);
    }

    private void setCartesianDrive(double x, double y, double z) {
        RobotMap.robotdrive.driveCartesian(x, -y, z);
    }

    private void setTarget(double target) {
        gyroLock.setSetpoint(target);
    }

    private void enableLock(boolean en) {
        enLock = en;
        if (enLock) {
        	gyroLock.enable();
        }
        else {
        	gyroLock.disable();
        }
    }

    private double[] getAdjStick() {
        double[] out = new double[3];
        out[0] = evalDeadBand(Robot.oi.getMasterStick().getX(), deadBandVal);
        out[1] = evalDeadBand(Robot.oi.getMasterStick().getY(), deadBandVal);
        out[2] = evalDeadBand(Robot.oi.getMasterStick().getZ(), deadBandVal);
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
