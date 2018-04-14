package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;


public class UnlockLift extends Command {
	
	public UnlockLift() {
		
	}

    @Override
    protected void initialize() {
        RobotMap.liftBot.unlock();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
