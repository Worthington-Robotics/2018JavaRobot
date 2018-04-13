package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

/**
 *
 */
public class GyroLock extends Command {

    public GyroLock() {
        // Use requires() here to declare subsystem dependencies
        //requires(RobotMap.robotDriveV4);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        RobotMap.robotDriveV4.enableTo(RobotMap.robotDriveV4.getGyro(), true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        RobotMap.robotDriveV4.enableTo(0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
