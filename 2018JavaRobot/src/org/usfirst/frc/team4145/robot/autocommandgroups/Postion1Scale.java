package org.usfirst.frc.team4145.robot.autocommandgroups;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.QueueGroup;

public class Postion1Scale extends QueueGroup {
    public Postion1Scale(int autonumber){
    	if(autonumber == 0){ //If it is the 1st auto assignment
    		addParallel(new Command[]{new DriveTo(19*299.65), new LiftToPosition(500)},1000);
    		addParallel(new LiftToPosition(500));
    		addSequential(new GyroToAngle(110), 500);
    		addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500)}, 500);
    		addParallel(new Command[]{new DriveTo(19*12)},1000);
    		addSequential(new DropForks(),200);
    		
    			addSequential(new DropCube(), 100);
    		
    		
    	}
    	if(autonumber == 1){
    		addParallel(new Command[]{new DriveTo(19*60)},1500);
    		addSequential(new GyroToAngle(90), 500);
    		addParallel(new Command[]{new DriveTo(19*190)},1000);
    		addSequential(new GyroToAngle(81.5), 500);
    		addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500)}, 500);
    		addParallel(new Command[]{new DriveTo(19*220)},1500);
    		addSequential(new DropForks(), 200);
    		
    			addSequential(new DropCube(), 100);
    	}
    }
}
 