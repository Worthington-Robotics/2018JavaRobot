package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.commands.testonly.LowerLiftTest;
import org.usfirst.frc.team4145.robot.commands.testonly.MotorBaseTest1;
import org.usfirst.frc.team4145.robot.commands.testonly.RoboLiftTest;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;

public class AutoTestProct extends CommandGroupV2 {

	public AutoTestProct() {
		//addSequential(new MotorBaseTest1(), 4020);
		addParallel(new Command[] {new LowerLiftTest(), new MotorBaseTest1(), new ForkTest(), new RoboLiftTest()}, 20000);
		//addSequential(new HigherLiftTest(), 1000000);
	}
	

}
