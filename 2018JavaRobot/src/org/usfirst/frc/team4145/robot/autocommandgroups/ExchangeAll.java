package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class ExchangeAll extends QueueGroup {

	public ExchangeAll(int autonumber) {
		if (autonumber == 2) {
			// Center
			addSequential(new DriveTo(19 * 20, false), 1000);
			addSequential(new GyroToAngle(-90), 1000);
			addSequential(new DriveTo(19 * 67, false), 3000);
			addSequential(new GyroToAngle(-90), 1000);
			addParallel(new Command[] {new DriveTo(19 * 20,false), new DropForks()}, 500);
			addSequential(new DropCube(true), 1000);
			addSequential(new GyroToAngle( 200), 2000);
			addSequential(new DriveTo(19 * 89,false),3000);
			addSequential(new PickCube(), 2000);
		}
		if (autonumber == 1) {
			// Far Left
			addSequential(new DriveTo(19 * 40, false), 2000);
			addSequential(new GyroToAngle(88), 2000);
			addSequential(new DriveTo(19 * 85, false), 3000);
			addSequential(new GyroToAngle(87), 2000);
			addParallel(new Command[] {new DriveTo(19 * 20, false), new DropForks()}, 2000);
			addSequential(new DropCube(true), 2000);
			addSequential(new GyroToAngle(200), 2000);
			addSequential(new DriveTo(19 * 80, false),3000);
			addSequential(new PickCube(), 2000);
		}
		if (autonumber == 3) {
			// Far right
			addSequential(new DriveTo(19 * 20, false), 1000);
			addSequential(new GyroToAngle(-90), 1000);
			addSequential(new DriveTo(19 * 144,false), 4000);
			addSequential(new GyroToAngle(-90), 1000);
			addParallel(new Command[] {new DriveTo(19 * 20, false), new DropForks()}, 1000);
			addSequential(new DropCube(true), 1000);
			addSequential(new GyroToAngle(200), 2000);
			addSequential(new DriveTo(19 * 89, false),3000);
			addSequential(new PickCube(), 2000);
		}
	}
}
