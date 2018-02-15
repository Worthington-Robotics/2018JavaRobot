package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PhillipExchange extends QueueGroup {

	public PhillipExchange(int autonumber) {
		if (autonumber == 1) {
			// Go forward
			addParallel(new Command[] { DriveTo(19 * 10), DropForks(),
					GyroToAngle(RobotMap.Drive.getGyro()) });
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() + 90));
			addSequential(new DriveTo(19 * 40));
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() + 90));
			addSequential(new DriveTo(19 * 10));
			addSequential(new DropCube());
		}
		if (autonumber == 2) {
			// Go forward
			addParallel(new Command[] { DriveTo(19 * 10), DropForks(),
					GyroToAngle(RobotMap.Drive.getGyro()) });
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90));
			addSequential(new DriveTo(19 * 90));
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90));
			addSequential(new DriveTo(19 * 10));
			addSequential(new DropCube());
		}
		if (autonumber == 3) {
			// Go forward
			addParallel(new Command[] { DriveTo(19 * 10), DropForks(),
					GyroToAngle(RobotMap.Drive.getGyro()) });
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() - 90));
			addSequential(new DriveTo(19 * 140));
			addSequential(new GyroToAngle(RobotMap.Drive.getGyro() + -90));
			addSequential(new DriveTo(19 * 10));
			addSequential(new DropCube());
		}
	}
}
