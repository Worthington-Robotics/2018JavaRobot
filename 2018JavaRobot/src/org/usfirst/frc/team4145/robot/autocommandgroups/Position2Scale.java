package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class Position2Scale extends QueueGroup {
	
	public Position2Scale(int autonumber) {
		if (autonumber == 0) {
			//Forward then rotate
			addDrive("/home/lvuser/MotionProfile/Position2Scale0_left_detailed.csv", "/home/lvuser/MotionProfile/Position2Scale0_right_detailed.csv");
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500), new DropForks()}, 500);
			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 100);
		}
		if (autonumber == 1) {
			//Forward then rotate
			addDrive("/home/lvuser/MotionProfile/Position2Scale1_left_detailed.csv", "/home/lvuser/MotionProfile/Position2Scale1_right_detailed.csv");
			addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(500), new DropForks()},  500);
			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 100);
		}
	}

}
