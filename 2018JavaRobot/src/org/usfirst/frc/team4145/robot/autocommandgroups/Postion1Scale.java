package org.usfirst.frc.team4145.robot.autocommandgroups;


import edu.wpi.first.wpilibj.command.QueueGroup;

public class Postion1Scale extends QueueGroup {
    public Postion1Scale(int autonumber){
    	if(autonumber == 0){ //If it is the 1st auto assignment
    		addParallel(new Command[]{new DriveTo(19*299.65)), new GyroToAngle(RobotMap.drive.getGyro()), new DropForks(), new LiftToPosition(1000)});
    		
    		addParallel(new Command[]{GyroToAngle(RobotMap.drive.getGyro()+90), HighLiftUp()});
    		
    		addSequential(new DropCube);
    		
    		addParallel(new Command[]{HighLiftDown()), new LiftToPosition(0), new GyroToAngle(RobotMap.drive.getGyro()+90)});
    	}
    	if(autonumber == 1){
    		addParallel(new Command[]{new DriveTo(19*228.735)), addSequential( GyroToAngle(), new DropForks(), new LiftToPosition(1000)};
    		
    		addParallel(new Command[]{new GyroToAngle(RobotMap.drive.getGyro()+90), new DriveTo(19*190)});
    		
    		addParallel(new Command[]{new GyroToAngle(RobotMap.drive.getGyro()-90), new DriveTo(19*70.915), new HighLiftUp()}); 
    	}
    }
}
 