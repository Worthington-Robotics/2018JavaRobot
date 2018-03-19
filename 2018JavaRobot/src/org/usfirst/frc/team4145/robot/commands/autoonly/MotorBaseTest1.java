package org.usfirst.frc.team4145.robot.commands.autoonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorBaseTest1 extends Command {
	private boolean Test1pt1;
	private boolean Test1pt2;
	private int iterations = 0;

	public void initialize() {
		RobotMap.driveFrontLeft.setSpeed(.254);
	}

	@Override
	protected boolean isFinished() {
		return iterations > 705;
	} // has to finish at end of auto routine

	public void execute() {
		iterations++;
		if (iterations > 50) {
			RobotMap.driveFrontLeft.setSpeed(0);
			RobotMap.driveFrontRight.setSpeed(.254);
		}
		if (iterations < 100 && iterations > 50) {
			RobotMap.driveFrontRight.setSpeed(0);
			RobotMap.driveRearRight.setSpeed(.254);
		}
		if (iterations < 150 && iterations > 50) {
			RobotMap.driveRearRight.setSpeed(0);
			RobotMap.driveRearLeft.setSpeed(.254);}
		if(iterations < 175 && iterations > 150)
		{RobotMap.driveRearLeft.setSpeed(0);}
		if(iterations > 175 && iterations < 702)
		{RobotMap.driveFrontRight.setSpeed(.5);
		RobotMap.driveFrontLeft.setSpeed(.5);
		}
	}

	public void end() {
		
		SmartDashboard.putBoolean("Motor Check Test", RobotMap.rightWheelEncoder.getDistance() / 10 == 10 && RobotMap.leftWheelEncoder.getDistance() / 10 == 10);
	}

	public void interrupted() {
		end();
	}
}
