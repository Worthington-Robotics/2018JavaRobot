package org.usfirst.frc.team4145.robot.commands;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Pickup extends Command{
	
	private boolean isDone = false;
	
	public Pickup(){
		requires(RobotMap.CubeManipulator);
	}

	public void initialize(){
		RobotMap.CubeManipulator.pickup();
		// starts motors
	}
	
	public void execute(){
		// cole.exe.executed.numnumnum
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		isDone = true;	
		
	}
	
	
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
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
