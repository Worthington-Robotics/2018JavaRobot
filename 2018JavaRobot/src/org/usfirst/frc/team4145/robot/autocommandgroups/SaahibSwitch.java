package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.QueueGroup;

public class SaahibSwitch extends QueueGroup {
	public SaahibSwitch(int autonumber){
		if(autonumber == 0){
    		addParallel(new Command[]{new DriveTo(19 * 140), new DropForks()}, 1500);
    		addSequential(new DropCube(), 200);
		}
		if(autonumber == 1 ){
			addParallel(new Command[]{new DriveTo(19 * 0), new DropForks()}, 1500); 
			addSequential(new GyroToAngle(31.399));
			addParallel(new Command[]{new DriveTo(19 * 180.422), new DropForks()}, 1500);
			addSequential(new GyroToAngle(-33.399));
			addSequential(new DriveTo(19 * 5), 3000);
			addSequential(new DropCube(), 200);
		}
    }
}
