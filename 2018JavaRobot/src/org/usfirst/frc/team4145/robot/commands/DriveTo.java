package org.usfirst.frc.team4145.robot.commands;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.team4145.robot.shared.AccessiblePIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTo extends Command {

	private int length = 0;
	private PIDController driveTo;
	private AccessiblePIDOutput output;
	private double[] toSet = { 0.0, 0.0, 0.0 };

	//constructor to initialize stuff
	public DriveTo(int count) {
		output = new AccessiblePIDOutput();
		length = count;
		driveTo = new PIDController(0.1, 0.1, 0.1, RobotMap.driveEncoder, output);
		driveTo.setAbsoluteTolerance(3);
	}

	// Set Setpoint to length
	protected void initialize() {
		RobotMap.Drive.enableTo(RobotMap.ahrs.getYaw(), true);
		driveTo.setSetpoint(length);
		driveTo.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	// Set coordinates for the robot to move to
	protected void execute() {
		toSet[1] = output.getValue();
		RobotMap.Drive.setInput(toSet);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return driveTo.onTarget();
	}

	// Called once after isFinished returns true and disable driveTo
	protected void end() {
		RobotMap.Drive.enableTo(0, false);
		driveTo.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
