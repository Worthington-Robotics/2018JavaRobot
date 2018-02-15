package org.usfirst.frc.team4145.robot.autocommandgroups;


import edu.wpi.first.wpilibj.command.QueueGroup;

public class Postion1Scale extends QueueGroup {
    public Postion1Scale(int autonumber){
    	if(autonumber == 0){ //If it is the 1st auto assignment
    		addParallel(new Command[]{new DriveTo(19*299.65),new DropForks(), new LiftToPosition(500)},1000);
    		
    		addParallel(new LiftToPosition(500));
    		
    		addSequential(new GyroToAngle(110), 1500);
    		
    		addParallel(new Command[]{new DriveTo(19*12),new DropForks(), new LiftToPosition(1000)},1000);
    		
    		addSequential(new DropCube);
    		
    		
    	}
    	if(autonumber == 1){
    		addParallel(new Command[]{new DriveTo(19*60), new LiftToPosition(500)},1500);
    		
    		addSequential(new GyroToAngle(90), 500);
    		
    		addParallel(new Command[]{new DriveTo(19*190),new DropForks(), new LiftToPosition(500)},1000);
    		
    		addSequential(new GyroToAngle(81.5), 500);
    		
    		addParallel(new Command[]{new DriveTo(19*220), new LiftToPosition(1000)},1500);
    		
    		addSequential(new DropCube);
    	}
    }
}
 