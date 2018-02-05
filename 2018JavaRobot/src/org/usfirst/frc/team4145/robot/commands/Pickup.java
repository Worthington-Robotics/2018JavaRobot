package org.usfirst.frc.team4145.robot.commands;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Pickup extends Command{
	
	private boolean isDone = false;
	private int Counter = 0;
	private int TimeOut = 100;
	
	public Pickup(){
		requires(RobotMap.CubeManipulator);
	}

	public void initialize(){
		RobotMap.CubeManipulator.pickup();
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
		RobotMap.CubeManipulator.stall();
	// Cole made me do this so
		}
	  
	
	public void interrupted (){
		end();
	}
	
}
