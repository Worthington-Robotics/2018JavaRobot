package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.team4145.robot.shared.QueueGroup;

public class AdamScale extends QueueGroup {
	
	public AdamScale(int autonumber) {
		if (autonumber == 0) {
			//Forward toward scale
			addParallel(new Command[] {new DriveTo(19 * 270)}, 500);
			
			//Rotation
			addSequential(new GyroToAngle(-45), 300);
			
			//Fork Stuff
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500)}, 500);
			
			addSequential(new DropForks(), 200);
			addSequential(new DropCube(), 100);
		if (autonumber == 1) {
			//Forward
			addParallel(new Command[] {new DriveTo(19 * 190)}, 300);
			
			//Rotation
			addSequential(new GyroToAngle(-90), 100);
			
			//Forward
			addSequential(new DriveTo(19 * 170));
			
			//Rotation
			addSequential(new GyroToAngle(90), 100);
			
			//Fork Stuff
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500)}, 500);
			
			//Forward and Drop
			addSequential(new DriveTo(19 * 80), 100);
			addSequential(new DropForks(), 200);
			addSequential(new DropCube(), 100);
		}
		}
	}
}
