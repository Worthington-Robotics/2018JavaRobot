package org.usfirst.frc.team4145.robot.commands.testonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RoboLiftTest extends Command {

	private int iterations = 0;

	

	public void initialize() {
		RobotMap.liftBot.lock();
		iterations = 0;
	}

	@Override
	protected boolean isFinished() {
		return iterations == 200;
	} // has to finish at end of auto routine

	public void execute() {
		iterations ++;
		if(iterations == 100)
		{
			RobotMap.liftBot.unlock();
		}

	}

	public void end() {
		RobotMap.liftBot.unlock();
	}

	public void interrupted() {
		end();
	}
}
