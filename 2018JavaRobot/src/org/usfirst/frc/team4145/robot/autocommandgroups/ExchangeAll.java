package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class ExchangeAll extends QueueGroup {

	public ExchangeAll(int autonumber) {
		if (autonumber == 2) {
			// Center
			addDrive("Exchange1All2_left.csv","Exchange1All2_right.csv");
			addParallel(new Command[] {new DropForks()}, 500);
			addSequential(new DropCube(true), 1000);
			addSequential(new PickCube(), 2000);
		}
		if (autonumber == 1) {
			// Far Left
			addDrive("Exchange1All1_left.csv","Exchange1All1_right.csv");
			addParallel(new Command[] {new DropForks()}, 2000);
			addSequential(new DropCube(true), 2000);
			addSequential(new PickCube(), 2000);
		}
		if (autonumber == 3) {
			// Far right
			addDrive("Exchange1All3_left.csv","Exchange1All3_right.csv");
			addParallel(new Command[] {new DropForks()}, 1000);
			addSequential(new DropCube(true), 1000);
			addSequential(new PickCube(), 2000);
		}
	}
}
