package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.HigherLiftTest;
import org.usfirst.frc.team4145.robot.commands.autoonly.LowerLiftTest;
import org.usfirst.frc.team4145.robot.commands.autoonly.MotorBaseTest1;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTestProct extends QueueGroup {

	private boolean lift, forks, leftMotor, rightMotor;

	public AutoTestProct() {
		addParallel(new Command[] {new LowerLiftTest(), new MotorBaseTest1()}, 7500);
		addSequential(new HigherLiftTest(), 7500);
	}
	

}
