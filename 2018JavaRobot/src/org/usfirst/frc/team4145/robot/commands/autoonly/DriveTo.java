package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
@Deprecated
public class DriveTo extends Command implements PIDOutput, PIDSource {

	private int length = 0;
	private PIDController driveTo;
	private double[] toSet = { 0.0, 0.0, 0.0 };
	
	private double kP = 0.0040; //nominal 0.0040
	private double kI = 0.0000; //nominal 0.0000
	private double kD = 0.0250; //nominal 0.0250
	private double FORWARD_AUTHORITY = 0.60; //nominal 0.4
	private double REVERSE_AUTHORITY = 0.60; //nominal 0.6

	// constructor to initialize stuff
	public DriveTo(int count) {
		length = count;
		driveTo = new PIDController(kP, kI, kD, this, this::pidWrite);
		//driveTo.setAbsoluteTolerance(0);
		driveTo.setContinuous(false);
		if(count > 0)
			driveTo.setOutputRange(-FORWARD_AUTHORITY, FORWARD_AUTHORITY);
		else
			driveTo.setOutputRange(-REVERSE_AUTHORITY,REVERSE_AUTHORITY);
	}

	// Set Setpoint to length
	protected void initialize() {
		//RobotMap.drive.setDynamicBrakeMode(new boolean[] {true, false, true, false});
		//RobotMap.drive.enableTo(RobotMap.drive.getGyro(), true);
		RobotMap.rightWheelEncoder.reset();
		SmartDashboard.putNumber("Wheel Encoder Target", length);
		driveTo.setSetpoint(length);
		driveTo.setEnabled(true);
	}

	// Called repeatedly when this Command is scheduled to run
	// Set coordinates for the robot to move to
	protected void execute() {
		//SmartDashboard.putNumber("PID target", driveTo.getSetpoint());
		//SmartDashboard.putNumber("EncoderCount", pidGet());
		//SmartDashboard.putNumber("PID ERROR", driveTo.getError());
		//SmartDashboard.putNumber("PID output", driveTo.get());
		//SmartDashboard.putBoolean("PID Enabled", driveTo.isEnabled());
	}

	@Override
	public double pidGet() {
		return RobotMap.rightWheelEncoder.get();
	}

	@Override
	public void pidWrite(double output) {
		//System.out.println("Pid Written to: " + output);
		//System.out.println("Encoder Value at call: " + pidGet());
		toSet[0] = -output;
		//RobotMap.drive.setInput(toSet);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false; //return driveTo.onTarget();
	}

	// Called once after isFinished returns true and disable driveTo
	protected void end() {
		//RobotMap.drive.enableTo(0, false);
		RobotMap.rightWheelEncoder.reset();
		driveTo.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

}
