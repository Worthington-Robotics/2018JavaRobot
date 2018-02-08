package org.usfirst.frc.team4145.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import org.usfirst.frc.team4145.robot.RobotMap;

public class BotUp extends Command {

	public BotUp() {
		requires(RobotMap.liftbot);
	}
	
    public void initialize(){
    	
        RobotMap.liftBotMotor.set(0.5);
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
