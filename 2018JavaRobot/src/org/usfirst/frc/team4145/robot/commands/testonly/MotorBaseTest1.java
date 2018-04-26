package org.usfirst.frc.team4145.robot.commands.testonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorBaseTest1 extends Command {
	private double[] power = {-.5,0,0}, noPower = {0,0,0};
	private int iterations = 0;

	public MotorBaseTest1(){

	}

	public void initialize() {
		RobotMap.robotDriveV4.configTeleop();
		RobotMap.robotDriveV4.setOperatorInput(power);
		iterations = 0;
		RobotMap.robotDriveV4.reset();
	}

	@Override
	protected boolean isFinished() {
		return iterations > 200;
	} // has to finish at end of auto routine

	public void execute() {
		iterations++;
	}

	public void end() {
		RobotMap.robotDriveV4.setOperatorInput(noPower);
		SmartDashboard.putBoolean("Motor Check Test",(RobotMap.robotDriveV4.getRightEncoder() > 113000 && RobotMap.robotDriveV4.getRightEncoder() < 115000)
				&& (RobotMap.robotDriveV4.getLeftEncoder() > 113000 && RobotMap.robotDriveV4.getLeftEncoder() < 115000));
	}

	public void interrupted() {
		end();
	}
}
