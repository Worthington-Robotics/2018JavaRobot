package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulation extends Subsystem {


	public CubeManipulation() {

	}

	@Override
	public void initDefaultCommand() {
		setPower(0);
	}

	public void setPower(double power){
		RobotMap.clampL.set(power);
		RobotMap.clampR.set(-power);
	}

	public void stall() {
		RobotMap.clampL.set(0);
		RobotMap.clampR.set(0);
	}

	public void dropStop() {
		RobotMap.dropper.set(0);
	}

	public void dropStart() {
		RobotMap.dropper.set(-0.5);
	}

	public void lift() {
		RobotMap.dropper.set(0.5);
	}
}
