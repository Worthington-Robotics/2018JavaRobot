package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;


public class Position1Switch extends QueueGroup {
	public Position1Switch(int autonumber){
		if(autonumber == 0){
    		addParallel(new Command[]{new DriveTo(19 * 194),new LiftToPosition(1000)}, 3000);
    		addSequential(new GyroToAngle(90), 1000);
    		addSequential(new DropForks(), 1000);
    		addSequential(new DropCube(false), 1000);
		}
		if(autonumber == 1 ){
			addParallel(new Command[]{new DriveTo(19 * 200)}, 2000); 
			addSequential(new GyroToAngle(90), 1000);
			addParallel(new Command[]{new DriveTo(19 * 154),new LiftToPosition(1000)}, 4000);
			addSequential(new GyroToAngle(90), 1000);
			addParallel(new Command[] {new DriveTo(19 * 5), new DropForks()}, 3000);
			addSequential(new DropCube(false), 1000);
		}
    }
}
