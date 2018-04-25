package org.usfirst.frc.team4145.robot.commands.autoonly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorBaseTest1 extends Command {
	private double[] power = {-.5,0,0}, noPower = {0,0,0};
	private int iterations = 0;

	public MotorBaseTest1(){

	}

	public void initialize() {
		RobotMap.drive.getTeleopDriveInstance().setDriveSet(power);
		iterations = 0;
		RobotMap.drive.resetEncoders();
	}

	@Override
	protected boolean isFinished() {
		return iterations > 200;
	} // has to finish at end of auto routine

	public void execute() {
		iterations++;
	}

	public void end() {
		RobotMap.drive.getTeleopDriveInstance().setDriveSet(noPower);
		SmartDashboard.putBoolean("Motor Check Test",(RobotMap.drive.getLeftEncoder() > 113000 && RobotMap.drive.getLeftEncoder() < 115000)
				&& (RobotMap.drive.getRightEncoder() > 113000 && RobotMap.drive.getRightEncoder() < 115000));
	}

	public void interrupted() {
		end();
	}
}
