/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import org.usfirst.frc.team4145.robot.subsystems.ExampleSubsystem;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//public variables
	public static String GameData = null; //field alliance data about switch / scale 
	
	//actuators
	
	//sensors
	
	//subsystems public static final
	public static ExampleSubsystem exampleSystem;
	
	public static void init() {
		//all general objects instantated here
		
		//all actuator objects here
		
		//all sensor objects here
		
		//create all subsystem objects
		exampleSystem = new ExampleSubsystem();
		
		
	}
}
