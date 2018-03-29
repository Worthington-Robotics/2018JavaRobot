package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTestProct extends QueueGroup {

	public AutoTestProct() {
		//addSequential(new MotorBaseTest1(), 4020);
		addParallel(new Command[] {new LowerLiftTest(), new MotorBaseTest1(), new ForkTest(), new RoboLiftTest()}, 20000);
		//addSequential(new HigherLiftTest(), 1000000);
	}
	

}
