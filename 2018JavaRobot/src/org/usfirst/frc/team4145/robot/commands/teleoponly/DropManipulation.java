package org.usfirst.frc.team4145.robot.commands.teleoponly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DropManipulation extends Command{
	
	private boolean isDone = false;
	
	public DropManipulation(){
		requires(RobotMap.CubeManipulator);
		
	}
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		RobotMap.CubeManipulator.dropStart();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isDone;
		// tells when done
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		RobotMap.CubeManipulator.dropStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}