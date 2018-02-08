/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Liftbot extends Subsystem {

	// here. Call these from Commands.
	public void initDefaultCommand() {
		//RobotMap.liftBotMotor.set(0);
	}

	public void extend() {
		RobotMap.liftBotMotor.set(0.5);
	}

	public void pull() {
		RobotMap.liftBotMotor.set(-0.5);
	}

	public void stop() {
		RobotMap.liftBotMotor.set(0);
	}

	public void lock() {
		RobotMap.liftLock.set(true);
	}

	public void unlock() {
		RobotMap.liftLock.set(false);
	}

	@Override
	public void periodic() {
		//watchDog();
		//armLock();
	}

	public void watchDog() {

	}

	public void armLock() {
		
	}

}
