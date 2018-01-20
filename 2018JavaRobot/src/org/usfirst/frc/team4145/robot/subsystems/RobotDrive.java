package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.team4145.robot.shared.AccessiblePIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDrive extends Subsystem {
	private boolean enable = false;
	private PIDController GyroLock;
	private AccessiblePIDOutput Output;
	private double x, y, z;
	private double setPoint = 0;
	private double deadBandVal = 0.15;
	private double[] lastInputSet = { 0, 0, 0 };

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public RobotDrive() {
		Output = new AccessiblePIDOutput();
		GyroLock = new PIDController(0.025, 0, 0.025, RobotMap.ahrs, Output);
		GyroLock.setAbsoluteTolerance(0.5);
		this.setTarget(setPoint);
		// PID controller configs and output
	}

	@Override
	public void initDefaultCommand() {
		this.Drive(0, 0, 0);
	}

	@Override
	// periodic method runs roughly 50 times per second (20 ms)
	public void periodic() {
		lastInputSet = getAdjStick();
		SmartDashboard.putNumberArray("compensated stick values", lastInputSet);
		SmartDashboard.putNumber("Gyro Target", setPoint+180);
		SmartDashboard.putNumber("Gyro Angle", RobotMap.ahrs.getYaw()+180);
		if (enable) {
			// Periodically updates while gyro locked
			Drive(lastInputSet[0], lastInputSet[1], Output.getValue());

		} else {
			// periodically updates drive
			Drive(lastInputSet[0], lastInputSet[1], lastInputSet[2]);
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

	// Sets status of the PID controller
	// also tells the update method to use the gyro lock pid
	public void enableLock(boolean en) {
		enable = en;
		if (enable) {
			GyroLock.enable();// if true enable lock.
		} else {
			GyroLock.disable(); // if boolean is false disable lock
		}
	}

	public void setTarget(double rot) {
		setPoint = rot;
		GyroLock.setSetpoint(rot);
	}

	public void setPID(double p, double i, double d) {
		GyroLock.setPID(p, i, d);
	}

	// enable with parameters
	public void enWithParam(double p, double i, double d, double rot) {
		setPID(p, i, d);
		setTarget(rot);
		enableLock(true);
	}// use this method to set joystick values

	public void enableTo(double rot, boolean en) {
		this.setTarget(rot);
		this.enableLock(en);
	}

	public void setXYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// passes through cartesian parameters. do not use for drive!
	public void Drive(double x, double y, double z) {
		RobotMap.robotdrive.driveCartesian(x, -y, z);
		// passes through cartesian parameters
	}

}
