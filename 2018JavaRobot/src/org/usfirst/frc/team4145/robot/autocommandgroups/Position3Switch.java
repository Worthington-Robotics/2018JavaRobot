package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;


public class Position3Switch extends QueueGroup {
	public Position3Switch(int autonomous) {

		if (autonomous == 0) {
			//Forward 109 inches, raise lift, drop forks
			//addDrive("/home/lvuser/MotionProfile/Position3Switch0_left_detailed.csv", "/home/lvuser/MotionProfile/Position3Switch0_right_detailed.csv");
			addParallel(new Command[] {new LiftToPosition(1000)}, 2500);
			addSequential(new DropForks(), 200);
			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 200); // launch cube
		}
		else {
			//addDrive("/home/lvuser/MotionProfile/Position3Switch1_left_detailed.csv", "/home/lvuser/MotionProfile/Position3Switch1_right_detailed.csv");
			addParallel(new Command[] {new LiftToPosition(1000), new DropForks()}, 2000);
			addSequential(new DropForks(), 200);
			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 500);//launch cube
		}
	}
}