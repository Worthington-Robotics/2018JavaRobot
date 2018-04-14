package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;


public class LockLift extends Command {

	public LockLift() {
		
	}
	
    @Override
    protected void initialize() {
        RobotMap.liftBot.lock();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
