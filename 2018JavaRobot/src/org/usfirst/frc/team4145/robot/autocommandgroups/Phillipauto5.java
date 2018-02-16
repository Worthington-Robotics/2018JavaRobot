package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PhillipExchange extends QueueGroup {

	public PhillipExchange(int autonumber) {
		if (autonumber == 2) {
			// Center
			addParallel(new Command[] { DriveTo(19 * 20), DropForks() }, 500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90), 250);
			addSequential(new DriveTo(19 * 67), 1500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90), 250);
			addSequential(new DriveTo(19 * 20), 500);
			addSequential(new DropCube(), 500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() + 200), 1000);
			addSequential(new DriveTo(19 * 89),1500);
			addSequential(new PickCube(), 1000);
		}
		if (autonumber == 1) {
			// Far Left
			addParallel(new Command[] { DriveTo(19 * 20), DropForks() }, 500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90), 250);
			addSequential(new DriveTo(19 * 40), 750);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90), 250);
			addSequential(new DriveTo(19 * 20), 500);
			addSequential(new DropCube(), 500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() + 200), 1000);
			addSequential(new DriveTo(19 * 89),1500);
			addSequential(new PickCube(), 1000);
		}
		if (autonumber == 3) {
			// Far right
			addParallel(new Command[] { DriveTo(19 * 20), DropForks() }, 500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90), 250);
			addSequential(new DriveTo(19 * 144), 2500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90), 250);
			addSequential(new DriveTo(19 * 20), 500);
			addSequential(new DropCube(), 500);
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() + 200), 1000);
			addSequential(new DriveTo(19 * 89),1500);
			addSequential(new PickCube(), 1000);
		}
	}
}
