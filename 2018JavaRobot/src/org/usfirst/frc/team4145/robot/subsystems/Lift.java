package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{
	

	@Override
	public void initDefaultCommand() {
		// TODO Auto-generated method stub
		RobotMap.liftmotor.set(0);
		
	}
	
	
	public void liftup ()
	{
		RobotMap.liftmotor.set(0.5);
		
	}

	public void liftdown ()
	{
		RobotMap.liftmotor.set(-0.5);
	}
	
	public void stoplift ()
	{
		RobotMap.liftmotor.set(0);
	
	}
	public void liftspeed(double n){
		RobotMap.liftmotor.set(n);
	}
	
	
}
