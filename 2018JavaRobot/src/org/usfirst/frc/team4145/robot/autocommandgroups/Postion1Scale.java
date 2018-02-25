package org.usfirst.frc.team4145.robot.autocommandgroups;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class Postion1Scale extends QueueGroup {

    public Postion1Scale(int autonumber){
    	//Left
    	if(autonumber == 0){ //If it is the 1st auto assignment
			addParallel(new Command[]{new DriveTo(RobotMap.CLOSE_SCALE_DISTANCE), new LiftToPosition(RobotMap.LIFT_TO_SCALE)},4500);
    		addParallel(new Command[] {new GyroToAngle(-RobotMap.CLOSE_SCALE_TURN), new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE)}, 1500);
    		addParallel(new Command[]{new DriveTo(RobotMap.CLOSE_SCALE_ROLL), new LiftToPosition(RobotMap.LIFT_TO_SCALE)},1500);
    		addSequential(new DropCube(), 1000);
    	}
    	//Right
    	if(autonumber == 1){
			addParallel(new Command[]{new DriveTo(RobotMap.FAR_SCALE_DISTANCE_1)},5000);
			addSequential(new GyroToAngle(RobotMap.FAR_SCALE_TURN_1), 1000);
			addParallel(new Command[]{new DriveTo(RobotMap.FAR_SCALE_DISTANCE_2), new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE)},4000);
			addParallel(new Command[] {new GyroToAngle(RobotMap.FAR_SCALE_TURN_2), new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE)}, 1000);
			addParallel(new Command[] {new HighLiftUp(), new DriveTo(RobotMap.FAR_SCALE_DISTANCE_3), new LiftToPosition(RobotMap.LIFT_TO_SCALE)}, 2000);
			addSequential(new DropCube(), 1000);
    	}
    }
}
 