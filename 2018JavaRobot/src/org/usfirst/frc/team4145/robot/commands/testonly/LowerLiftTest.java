package org.usfirst.frc.team4145.robot.commands.testonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LowerLiftTest extends Command {
	private boolean Test1pt1, Test1pt2, isReady;
	private double power = 0.6;
	private int iterations = 0;

	

	public void initialize() {
		RobotMap.lift.setSpeed(power);
		iterations = 0;
		Test1pt1 = false;
		Test1pt2 = false;
		isReady = false;
	}

	@Override
	protected boolean isFinished() {
		return isReady && Math.abs(RobotMap.liftEnc.getDistance()) > 640
				;
	} // has to finish at end of auto routine

	public void execute() {
		iterations ++;
		if(iterations <= 750 && RobotMap.liftMotorL.getSensorCollection().isFwdLimitSwitchClosed()) {
				Test1pt1 = true;
				RobotMap.lift.setSpeed(-power);
		}
		if(iterations <= 750 && RobotMap.liftMotorL.getSensorCollection().isRevLimitSwitchClosed()) {
				Test1pt2 = true;
				isReady = true;
				RobotMap.liftEnc.reset();
				RobotMap.lift.setSpeed(power);
		}
	}

	public void end() {
		RobotMap.lift.setSpeed(0);
		SmartDashboard.putBoolean("Lower Lift Test", Test1pt1 && Test1pt2);
	}

	public void interrupted() {
		end();
	}
}
