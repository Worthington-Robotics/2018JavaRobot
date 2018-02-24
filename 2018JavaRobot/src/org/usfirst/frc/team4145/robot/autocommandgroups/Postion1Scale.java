package org.usfirst.frc.team4145.robot.autocommandgroups;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class Postion1Scale extends QueueGroup {

	private int LEFT_LIFT_TO = 1000;

    public Postion1Scale(int autonumber){
    	//Left
    	if(autonumber == 0){ //If it is the 1st auto assignment
			addParallel(new Command[]{new DriveTo(-19*270), new LiftToPosition(LEFT_LIFT_TO)},4500);
    		addParallel(new Command[] {new GyroToAngle(-125), new HighLiftUp(), new LiftToPosition(LEFT_LIFT_TO)}, 1500);
    		addParallel(new Command[]{new DriveTo(19*17), new LiftToPosition(LEFT_LIFT_TO)},1500);
    		addSequential(new DropCube(), 1000);
    	}
    	//Right
    	if(autonumber == 1){
    		addParallel(new Command[]{new DriveTo(-19*220)},5000);
    		addSequential(new GyroToAngle(100), 1000);
    		//addParallel(new Command[]{new DriveTo(-19*215), new HighLiftUp()},4000);
    		//addParallel(new Command[] {new GyroToAngle(80), new HighLiftUp()}, 1000);
    		//addParallel(new Command[] {new HighLiftUp(), new DriveTo(19*38)}, 2000);
    		//ddSequential(new DropCube(), 1000);
    	}
    }
}
 