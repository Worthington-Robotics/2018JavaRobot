package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.team4145.robot.shared.QueueGroup;

public class AdamScale extends QueueGroup {
	
	public AdamScale(int autonumber) {
		if (autonumber == 0) {
			//Forward toward scale
<<<<<<< HEAD
			addSequential(new DriveTo(19 * 270), 500);
=======
			addParallel(new Command[] {new DriveTo(19 * 270)}, 500);
>>>>>>> 21338b9385bd8a051454943d874b35ba00d1dff3
			
			//Rotation
			addSequential(new GyroToAngle(-45), 300);
			
			//Fork Stuff
<<<<<<< HEAD
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500), new DropForks()} 500);
			addSequential(new DropCube(), 100);
		if (autonumber == 1) {
			//Forward
			addSequential(new DriveTo(19 * 190), 300);
=======
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500),new DropForks()}, 900);
			
			
			addSequential(new DropCube(), 100);
		if (autonumber == 1) {
			//Forward
			addParallel(new Command[] {new DriveTo(19 * 190)}, 300);
>>>>>>> 21338b9385bd8a051454943d874b35ba00d1dff3
			
			//Rotation
			addSequential(new GyroToAngle(-90), 100);
			
			//Forward
			addSequential(new DriveTo(19 * 170));
			
			//Rotation
			addSequential(new GyroToAngle(90), 100);
			
<<<<<<< HEAD
			//Forward
			addSequential(new DriveTo(19 * 80), 100);
			
			//Fork Stuff and Drop
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500), new DropForks() 500);
=======
			//Forward and Drop
			addSequential(new DriveTo(19 * 80), 100);
			
			//Fork Stuff
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500),new DropForks()}, 900);
			
>>>>>>> 21338b9385bd8a051454943d874b35ba00d1dff3
			addSequential(new DropCube(), 100);
		}
		}
	}
}
