package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulation extends Subsystem {

	public CubeManipulation() {

	}

	@Override
	public void initDefaultCommand() {
		RobotMap.ClampL.set(0);
		RobotMap.ClampR.set(0);
	}

	public void pickup() {
		RobotMap.ClampL.set(0.5);
		RobotMap.ClampR.set(-0.5);

	}

	public void release() {
		RobotMap.ClampL.set(-0.5);
		RobotMap.ClampR.set(0.5);

	}

	public void stall() {
		RobotMap.ClampL.set(0);
		RobotMap.ClampR.set(0);
	}

	public void dropStop() {
		RobotMap.Dropper.set(0);
	}

	public void dropStart() {
		RobotMap.Dropper.set(0.1);
	}

	public void lift() {
		RobotMap.Dropper.set(-0.1);
	}
}
