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
		RobotMap.clampL.set(0);
		RobotMap.clampR.set(0);
	}

	public void pickup() {
		RobotMap.clampL.set(pull);
		RobotMap.clampR.set(-pull);

	}

	public void release() {
		RobotMap.clampL.set(-push);
		RobotMap.clampR.set(push);
	}

	public void fire(){
		RobotMap.clampL.set(-shoot);
		RobotMap.clampR.set(shoot);
	}

	public void stall() {
		RobotMap.clampL.set(0);
		RobotMap.clampR.set(0);
	}

	public void dropStop() {
		RobotMap.dropper.set(0);
	}

	public void dropStart() {
		RobotMap.dropper.set(0.5);
	}

	public void lift() {
		RobotMap.dropper.set(-0.5);
	}
}
