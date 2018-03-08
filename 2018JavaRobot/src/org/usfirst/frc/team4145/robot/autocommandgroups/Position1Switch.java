package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;


public class Position1Switch extends QueueGroup {
	public Position1Switch(int autonumber){
		if(autonumber == 0){
    		addDrive("Position1Switch0_left.csv","Position1Switch0_right.csv");
			addParallel(new Command[]{new LiftToPosition(1000)}, 3000);
    		addSequential(new DropForks(), 1000);
    		addSequential(new DropCube(false), 1000);
		}
		if(autonumber == 1 ){
			addDrive("PositionSwith1_left.csv","Position1Switch1_right.csv"); 
			addParallel(new Command[]{new LiftToPosition(1000)}, 4000);
			addParallel(new Command[] {new DropForks()}, 3000);
			addSequential(new DropCube(false), 1000);
		}
    }
}
