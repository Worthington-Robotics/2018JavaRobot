package org.usfirst.frc.team4145.robot.commands;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Release extends Command{
	private boolean isDone = false;

	@Override
	protected boolean isFinished(){
		// TODO Auto-generated method stub
		return false;
	}
	
	public Release(){
		requires(RobotMap.CubeManipulator);
	}
	
	public void initialize(){
		RobotMap.CubeManipulator.release();
		// starts motors y'all
		}
	public void execute(){
		
	}
	
	public boolean isFinished1(){
		return isDone;
		}
	public void end(){
		RobotMap.CubeManipulator.stall();
	}
	
	public void interrupted(){
		end();
	}
}
