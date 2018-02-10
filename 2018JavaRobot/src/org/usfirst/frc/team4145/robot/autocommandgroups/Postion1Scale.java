package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.HighLiftUp;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Postion1Scale extends CommandGroup {
    public Postion1Scale(int autonumber){
    	if(autonumber == 1){ //If it is the 1st auto assignment
    		addParallel(new DriveTo(19*248)); //drive 248 inches
    		addParallel(new GyroToAngle(RobotMap.drive.getGyro())); //Actually drive straight forward
    		addParallel(new DropForks()); //what it says on the tin
    		addParallel(new LiftToPosition()); //Lift Stage 1 (the lower bit)
    		
    		addsequntial(new GyroToAngle(RobotMap.drive.getGyro()+110)); //Turn 110 degrees
    		addParallel(new DriveTo(19*12)); //Drive 12 inches
    		addParallel(new HighLiftUp(1000)); //Lift up top bit
    	}
    	if(autonumber == 3){
    		addParallel(new DriveTo(19*60)); //drive 60 inches
    		addParallel(new GyroToAngle(RobotMap.drive.getGyro())); //Actually drive straight forward
    		
    		addsequntial(new GyroToAngle(RobotMap.drive.getGyro()+90)); //Turn 90 degrees
    		addParallel(new DropForks()); //what it says on the tin
    		addParallel(new LiftToPosition()); //Lift Stage 1 (the lower bit)
    		
    		addsequntial(new GyroToAngle(RobotMap.drive.getGyro()-81.5)); //Turn 81.5 degrees the other direction
    		addParallel(new DriveTo(19*220)); //Drive 220 inches
    		addParallel(new HighLiftUp(1000)); //Lift up top bit
    	}
    }
}
