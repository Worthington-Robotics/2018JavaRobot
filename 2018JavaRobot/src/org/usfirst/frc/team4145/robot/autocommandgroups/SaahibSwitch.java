package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SaahibSwitch extends CommandGroup {
    public SaahibSwitch(int autonumber){
    	
    	
if(autonumber == 0);
    	
    	{addParallel(new DriveTo(19*140));
    	addParallel(new DropFork());
    	addParallel(new GyroToAngle(RobotMap.Drive.getGyro()));
    		
    }
if(autonumber == 1 );
    	
    	{addParallel(new DriveTo(19*0));
    	addParallel(new DropFork());
    	addParallel(new GyroToAngle(RobotMap.Drive.getGyro()));
    	addSequential(new GyroToAngle(RobotMap.Drive.getGyro()+31.399));
    	addSequential(new DriveTo(19*180.422));
    	addSequential(new GyroToAngle(RobotMap.Drive.getGyro(-31.399)));
    	addSequential(new DriveTo(19*5));
    }
if(autonumber == 2);
    	{addParallel(new DriveTo(19*50));
    	addParallel(new DropFork());
    	addParallel(new GyroToAngle(RobotMap.Drive.getGyro()));
    	addSequential(new GyroToAngle(RobotMap.Drive.getGyro()-90));
    	addSequential(new DriveTo(19*40));
    	addSequential(new GyroToAngle(RobotMap.Drive.getGyro()+90));
    	addSequential(new DriveTo(19*10));
}
    }
   }
