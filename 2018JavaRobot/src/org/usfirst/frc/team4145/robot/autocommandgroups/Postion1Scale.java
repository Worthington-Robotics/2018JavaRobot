package org.usfirst.frc.team4145.robot.autocommandgroups;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class Postion1Scale extends QueueGroup {
    public Postion1Scale(int autonumber){
    	//Left
    	if(autonumber == 0){ //If it is the 1st auto assignment
    		addParallel(new Command[]{new DriveTo(19*275), new LiftToPosition(2800)},6000);
    		addParallel(new Command[] {new GyroToAngle(55), new HighLiftUp()}, 2000);
    		addParallel(new Command[]{new DriveTo(19*20),new DropForks(),new HighLiftUp()},1000);
    		addSequential(new DropCube(), 1000);
    	}
    	//Right
    	if(autonumber == 1){
    		addParallel(new Command[]{new DriveTo(19*220)},5000);
    		addSequential(new GyroToAngle(90), 1000);
    		addParallel(new Command[]{new DriveTo(19*215), new LiftToPosition(2800)},4000);
    		addParallel(new Command[] {new GyroToAngle(-95), new HighLiftUp()}, 1000);
    		addParallel(new Command[] {new HighLiftUp(), new DriveTo(19*50)}, 2000);
    		addSequential(new DropCube(), 1000);
    	}
    }
}
 