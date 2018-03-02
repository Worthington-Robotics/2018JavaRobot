package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class Position2Scale extends QueueGroup {
	
	public Position2Scale(int autonumber) {
		if (autonumber == 0) {
			//Forward then rotate
			addSequential(new DriveTo(19 * 20), 500);
			addSequential(new GyroToAngle(90), 100);
			
			//Forward then rotate
			addSequential(new DriveTo(19 * 90), 500);
			addSequential(new GyroToAngle(-90), 100);
			
			//Forward then rotate and drop
			addSequential(new DriveTo(19 * 240), 500);
			addSequential(new GyroToAngle(45), 100);
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500), new DropForks()}, 500);
			addSequential(new DropCube(true), 100);
		}
		if (autonumber == 1) {
			//Forward then rotate
			addSequential(new DriveTo(19 * 60), 300);
			addSequential(new GyroToAngle(-90), 100);
			
			//Forward then rotate
			addSequential(new DriveTo(19 * 120), 500);
			addSequential(new GyroToAngle(90), 100);
			
			//Forward Toward Scale and Drop
			addSequential(new DriveTo(19 * 210), 500);
			addSequential(new GyroToAngle(45), 100);
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500), new DropForks()},  500);
			addSequential(new DropCube(true), 100);
		}
	}

}
