package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.QueueGroup;

public class SaahibSwitch extends QueueGroup {
	public SaahibSwitch(int autonumber){
		if(autonumber == 0){
    		addParallel(new Command[]{new DriveTo(19 * 194),new liftToPosition(1000)}, 1500);
    		addSequential(new GyroToAngle(90), 200);
    		addSequential(new DropForks(), 200);
    		addSequential(new DropCube(), 200);
		}
		if(autonumber == 1 ){
			addParallel(new Command[]{new DriveTo(19 * 200)}, 1500); 
			addSequential(new GyroToAngle(90));
			addParallel(new Command[]{new DriveTo(19 * 154),new liftToPosition(1000)}, 1500);
			addSequential(new GyroToAngle(90));
			addSequential(new DriveTo(19 * 5), 3000);
			addSequential(new DropForks(), 200);
			addSequential(new DropCube(), 200);
		}
    }
}
