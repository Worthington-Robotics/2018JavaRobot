package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MeinekeSwitch extends CommandGroup {
	public MeinekeSwitch(int autonomous) {

		if (autonomous == 0) {
			addParallel(new DriveTo(19 * 109)); // forward 109 inches
			addParallel(new LiftToPostiton(1000));//raise lift 
			addSequential(new DropForks());//drop forks
			addSequential(new GyroToAngle(-15));// turn left 15 degrees
			addSequential(new DropCube()); // launch cube
		}
		else
		{
			addSequential(new GyroToAngle(10));//turn right 10 degrees
			addSequential(new DriveTo(19*199));//forward 199 inches
			addSequential(new GyroToAngle(-90));//turn left 90 degrees
			addParallel(new LiftToPosition(1000));//raise lift
			addParallel(new DriveTo(19*189));//forward 189 inches
			addSequential(new DropForks());//drop forks
			addSequential(new GyroToAngle(-90));//turn left 90 degrees
			addSequential(DropCube());//launch cube
		}
	}
}