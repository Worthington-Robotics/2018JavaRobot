package org.usfirst.frc.team4145.robot.commands;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroLock extends Command {

	//double target = 0;
	private boolean isfin = false;
	
    public GyroLock() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.Drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.Drive.enableTo(RobotMap.ahrs.getYaw(), true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isfin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.Drive.enableTo(0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
