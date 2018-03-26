package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class Position3Scale extends QueueGroup {

	   public Position3Scale(int autonumber) {

	        //Right

	        if (autonumber == 0) {
	            addParallel(new Command[]{new DriveTo(Constants.CLOSE_SCALE_DISTANCE), new LiftToPosition(Constants.LIFT_TO_SCALE)},4500);
	            addParallel(new Command[] {new GyroToAngle(Constants.CLOSE_SCALE_TURN), new HighLiftUp(), new LiftToPosition(Constants.LIFT_TO_SCALE)}, 1500);
	            addParallel(new Command[]{new DriveTo(Constants.CLOSE_SCALE_ROLL), new HighLiftUp(), new LiftToPosition(Constants.LIFT_TO_SCALE), new DropForks()},1500);
				addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
	            addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1000);
	        }

	        //Left

	        if (autonumber == 1) {
	            addParallel(new Command[]{new DriveTo(Constants.FAR_SCALE_DISTANCE_1)}, 4000);
	            addSequential(new GyroToAngle(-Constants.FAR_SCALE_TURN_1),1000);
	            addParallel(new Command[]{new DriveTo(Constants.FAR_SCALE_DISTANCE_2), new HighLiftUp(), new LiftToPosition(Constants.LIFT_TO_SCALE)},4000);
	            addParallel(new Command[]{new GyroToAngle(-Constants.FAR_SCALE_TURN_2), new HighLiftUp(), new LiftToPosition(Constants.LIFT_TO_SCALE)}, 1000);
	            addParallel(new Command[]{new HighLiftUp(), new DriveTo(Constants.FAR_SCALE_DISTANCE_3), new LiftToPosition(Constants.LIFT_TO_SCALE)}, 2000);
	            addParallel(new Command[]{new DropForks(), new HighLiftUp()},1000);
				addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
	            addSequential(new CubeMovement(CubeMovement.CubeState.Shoot),1000);
	        }

	    }

	}

