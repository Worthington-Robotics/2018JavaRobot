/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import org.usfirst.frc.team4145.robot.subsystems.CubeManipulation;
import org.usfirst.frc.team4145.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4145.robot.subsystems.RobotDrive;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.Spark;
=======
import edu.wpi.first.wpilibj.drive.MecanumDrive;
>>>>>>> Robot_Test
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//public variables
	public static String GameData = null; //field alliance data about switch / scale 
	public static final String AutoList[] = {"Auto1","Auto2","Auto3","Auto4","Auto5",
			"Auto6","Auto7","Auto8","Auto9","Auto10","Auto11","Auto12","DO NOTHING"}; // three different auto positions
	//actuators
<<<<<<< HEAD
	public static TalonSRX Drive1,Drive2,Drive3,Drive4;
	public static Spark ClampL, ClampR, Dropper;
	

=======
	public static WPI_TalonSRX Drive1,Drive2,Drive3,Drive4; //need to use WPI_talonSRX for drivetrain use
	public static MecanumDrive robotdrive; //meccanum drive object
	
>>>>>>> Robot_Test
	//sensors
	public static AHRS ahrs; //AHRS system on navx
	public static Joystick stick1;
	
	
	//subsystems public static
	public static ExampleSubsystem exampleSystem;
<<<<<<< HEAD
	public static CubeManipulation CubeManipulator;
=======
	public static RobotDrive Drive;
>>>>>>> Robot_Test
	
	public static void init() {
		//all general objects instantated here
		SmartDashboard.putStringArray("Auto Selector", AutoList); //publishes the auto list to the dashboard "Auto Selector"
		
		//all actuator objects here
<<<<<<< HEAD
		Drive1 = new TalonSRX(1);
		Drive2 = new TalonSRX(2);
		Drive3 = new TalonSRX(3);
		Drive4 = new TalonSRX(4);
		ClampL = new Spark(1);
		ClampR = new Spark(2);
		Dropper = new Spark(3);

		
=======
		Drive1 = new WPI_TalonSRX(1); 
		Drive2 = new WPI_TalonSRX(2);
		Drive3 = new WPI_TalonSRX(3);
		Drive4 = new WPI_TalonSRX(4);
		robotdrive = new MecanumDrive(Drive1, Drive2, Drive3, Drive4); //create meccanum drive
>>>>>>> Robot_Test
		
		//all sensor objects here
		ahrs = new AHRS(SPI.Port.kMXP); //finish declaring AHRS to MXP SPI bus
		stick1 = new Joystick(0);
		
		//create all subsystem objects
		exampleSystem = new ExampleSubsystem();
<<<<<<< HEAD
		CubeManipulator = new CubeManipulation();
=======
		Drive = new RobotDrive();
		
		
>>>>>>> Robot_Test
	}
}
