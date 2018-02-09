package org.usfirst.frc.team4145.robot.commands.teleoponly;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Pickup extends Command{
	
	private boolean isDone = false;
	private int Counter = 0;
	private int TimeOut = 100;
	
	public Pickup(){
		requires(RobotMap.cubeManipulator);
	}

	public void initialize(){
		RobotMap.cubeManipulator.pickup();
		// starts motors
	}
	
	public void execute(){
		// cole.exe.executed.numnumnum		
	}
	
	
	@Override
	public boolean isFinished() {
		return isDone;
		// tells when done
	}
	
	public void end(){
		RobotMap.cubeManipulator.stall();
	// Cole made me do this so
		}
	  
	
	public void interrupted (){
		end();
	}
	
}
