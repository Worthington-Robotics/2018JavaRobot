package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;


public class Position3Switch extends QueueGroup {
	public Position3Switch(int autonomous) {

		if (autonomous == 0) {
			//Forward 109 inches, raise lift, drop forks
			addDrive("Position3Switch0_left.csv", "Position3Switch0_right.csv");
			addParallel(new Command[] {new LiftToPosition(1000)}, 2500);
			addSequential(new DropForks(), 200);
			addSequential(new DropCube(false), 200); // launch cube
		}
		else {
			addDrive("Position3Switch1_left.csv", "Position3Switch1_right.csv");
			addParallel(new Command[] {new LiftToPosition(1000), new DropForks()}, 2000);
			addSequential(new DropForks(), 200);
			addSequential(new DropCube(false), 500);//launch cube
		}
	}
}