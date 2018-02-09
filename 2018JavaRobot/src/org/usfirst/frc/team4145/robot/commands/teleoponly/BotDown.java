package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class BotDown extends Command{
	
	public BotDown() {
		requires(RobotMap.liftbot);
	}
	
    public void initialize(){
        RobotMap.liftBotMotor.set(-0.5);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public void end(){
        RobotMap.liftBotMotor.set(0.0);
    }

    public void interrupted(){
        end();
    }
}
