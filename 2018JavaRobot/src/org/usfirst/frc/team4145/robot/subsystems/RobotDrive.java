package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class RobotDrive extends Subsystem{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public void Drive(double x, double y, double z) {
		RobotMap.robotdrive.driveCartesian(y, x, z);
		//passes through cartesian parameters
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		this.Drive(0, 0, 0);
	}

}
