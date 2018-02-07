/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class LiftButtonUp extends Command {
	public LiftButtonUp() {
		// Use requires() here to declare subsystem dependencies
		//requires(RobotMap.lift);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() { 
		RobotMap.lift.liftspeedH(0.5);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println("LiftButtonUp Running:" + RobotMap.lift.getCurrentCommandName() + "  LiftmotorH code speed" + RobotMap.liftmotorH.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		RobotMap.lift.liftspeedH(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
