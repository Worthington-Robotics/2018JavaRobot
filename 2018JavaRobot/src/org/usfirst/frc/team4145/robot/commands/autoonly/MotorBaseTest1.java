package org.usfirst.frc.team4145.robot.commands.autoonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorBaseTest1 extends Command {
	private double power = .5;
	private int iterations = 0;

	public MotorBaseTest1(){

	}

	public void initialize() {
		RobotMap.driveFrontLeft.set(power);
		System.out.println("setting front left");
	}

	@Override
	protected boolean isFinished() {
		return iterations > 2000;
	} // has to finish at end of auto routine

	public void execute() {
		iterations++;
		if (iterations == 500) {
			System.out.println("setting front right");
			RobotMap.driveFrontLeft.set(0);
			RobotMap.driveFrontRight.set(power);
		}
		if (iterations == 1000) {
			System.out.println("Setting rear right");
			RobotMap.driveFrontRight.set(0);
			RobotMap.driveRearRight.set(power);
		}
		if (iterations == 1500) {
			System.out.println("setting rear left");
			RobotMap.driveRearRight.set(0);
			RobotMap.driveRearLeft.set(power);
		}
		if(iterations == 1750) {
			RobotMap.driveRearLeft.set(0);
		}
		if(iterations == 2000) {
			System.out.println("driving forward");
			RobotMap.driveFrontRight.set(power);
			RobotMap.driveFrontLeft.set(-power);
		}
	}

	public void end() {
		RobotMap.driveFrontRight.set(0);
		RobotMap.driveFrontLeft.set(0);
		SmartDashboard.putBoolean("Motor Check Test", (int)(RobotMap.rightWheelEncoder.getDistance()) / 10 == 10 && (int)(RobotMap.leftWheelEncoder.getDistance()) / 10 == 10);
	}

	public void interrupted() {
		end();
	}
}
