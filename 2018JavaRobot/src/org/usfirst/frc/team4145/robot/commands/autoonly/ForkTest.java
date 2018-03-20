package org.usfirst.frc.team4145.robot.commands.autoonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ForkTest extends Command {
	private boolean Test1pt1;
	private boolean Test1pt2;
	private double power = .5;
	private int iterations = 0;

	

	public void initialize() {
		RobotMap.clampL.set(power);
	}

	@Override
	protected boolean isFinished() {
		return iterations == 300;
	} // has to finish at end of auto routine

	public void execute() {
		iterations ++;
		if(iterations == 100)
		{
			RobotMap.clampL.set(0);
			RobotMap.clampR.set(power);
		}
		if(iterations == 200)
		{
			RobotMap.clampR.set(0);
		}
	}

	public void end() {
		RobotMap.clampR.set(0);
		RobotMap.clampL.set(0);
	}

	public void interrupted() {
		end();
	}
}

