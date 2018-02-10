package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropCube;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.HighLiftUp;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Postion1Scale extends CommandGroup {
    public Postion1Scale(int autonumber){
    	if(autonumber == 1){ //If it is the 1st auto assignment
    		addParallel(new DriveTo(19*299.65)); //drive 299.65 inches to Scale
    		addParallel(new GyroToAngle(RobotMap.drive.getGyro())); //Actually drive straight forward
    		addParallel(new DropForks()); //what it says on the tin
    		addParallel(new LiftToPosition(1000)); //Lift Stage 1 (the lower bit), Placeholder number
    		
    		addSequential(new GyroToAngle(RobotMap.drive.getGyro()+90)); //Turn 90 degrees
    		addParallel(new HighLiftUp()); //Lift up top bit
    		
    		addSequential(new DropCube); //off loads cube
    		
    		addSequential(HighLiftDown()); //Lowers high bit
    		addParallel(new LiftToPosition(0)); //Lowers low bit
    		addParallel(newGyroToAngle(RobotMap.drive.getGyro()+90)); //Finishes turn around
    		
    		//addSequential(new DriveTo(19*103.65)); //drive 103.65 inches to Switch
    	}
    	if(autonumber == 3){
    		addParallel(new DriveTo(19*228.735)); //drive 228.735 inches
    		addParallel(new GyroToAngle(RobotMap.drive.getGyro())); //Actually drive straight forward
    		addParallel(new DropForks()); //what it says on the tin
    		addParallel(new LiftToPosition(1000)); //Lift Stage 1 (the lower bit), Placeholder number
    		
    		addsequential(new GyroToAngle(RobotMap.drive.getGyro()+90)); //Turn 90 degrees
    		
    		addParallel(new DriveTo(19*190)); //drive 190 inches
    		
    		addsequential(new GyroToAngle(RobotMap.drive.getGyro()-81.5)); //Turn 81.5 degrees the other direction
    		addParallel(new DriveTo(19*70.915)); //Drive 70.915 inches
    		addParallel(new HighLiftUp()); //Lift up top bit
    	}
    }
}
