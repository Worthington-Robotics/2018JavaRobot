package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulation extends Subsystem {

	private double shoot = 1;
	private double pull = 0.5;
	private double push = 0.5;

	public CubeManipulation() {

	}

	@Override
	public void initDefaultCommand() {
		RobotMap.ClampL.set(0);
		RobotMap.ClampR.set(0);
	}

	public void pickup() {
		RobotMap.ClampL.set(pull);
		RobotMap.ClampR.set(-pull);

	}

	public void release() {
		RobotMap.ClampL.set(-push);
		RobotMap.ClampR.set(push);
	}

	public void fire(){
		RobotMap.ClampL.set(-shoot);
		RobotMap.ClampR.set(shoot);
	}

	public void stall() {
		RobotMap.ClampL.set(0);
		RobotMap.ClampR.set(0);
	}

	public void dropStop() {
		RobotMap.Dropper.set(0);
	}

	public void dropStart() {
		RobotMap.Dropper.set(0.5);
	}

	public void lift() {
		RobotMap.Dropper.set(-0.5);
	}
}
