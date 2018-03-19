package org.usfirst.frc.team4145.robot.commands.autoonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LowerLiftTest extends Command {
	private boolean Test1pt1;
	private boolean Test1pt2;
	private int iterations = 0;

	

	public void initialize() {
		RobotMap.liftMotorL.set(.254);
	}

	@Override
	protected boolean isFinished() {
		return Test1;
	} // has to finish at end of auto routine

	public void execute() {
		iterations ++;
		if(iterations <= 7500 && !RobotMap.liftMotorL.getSensorCollection().isRevLimitSwitchClosed())
		{
			if(RobotMap.liftMotorL.getSensorCollection().isFwdLimitSwitchClosed())
			{
				Test1pt1 = true;
				RobotMap.liftMotorL.set(-.254);
			}
		}
		if(iterations <= 7500 && !RobotMap.liftMotorL.getSensorCollection().isRevLimitSwitchClosed())
		{
			if(RobotMap.liftMotorL.getSensorCollection().isRevLimitSwitchClosed())
			{
				Test1pt2 = true;
				RobotMap.liftMotorL.set(0);
			}
		}
	}

	public void end() {
		SmartDashboard.putBoolean("Lower Lift Test", Test1pt1 && Test1pt2);
	}

	public void interrupted() {
		end();
	}
}
