package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class LiftToPosition extends Command{

	private int newCount;

	public LiftToPosition(int count) { //
		requires(RobotMap.lift);
		newCount = count;
	}



	public void initialize() {
		RobotMap.lift.enableTo(newCount, true);
	}


	public boolean isFinished() {
		return false; // If pid is at tolerance
	}

	public void end() {
		RobotMap.lift.enableTo(0, false);
	}

	public void interrupted() { //
		end();
	}

}