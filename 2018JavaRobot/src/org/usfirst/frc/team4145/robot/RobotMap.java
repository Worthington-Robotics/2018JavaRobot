/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import org.usfirst.frc.team4145.robot.subsystems.CubeManipulation;
import org.usfirst.frc.team4145.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4145.robot.subsystems.Lift;
import org.usfirst.frc.team4145.robot.subsystems.Liftbot;
import org.usfirst.frc.team4145.robot.subsystems.RobotDrive;
import org.usfirst.frc.team4145.robot.subsystems.RobotVision;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//public variables
	public static String GameData = null; //field alliance data about switch / scale 
	public static final String autoList[] = {"Auto1","Auto2","Auto3","Auto4","Auto5",
			"Auto6","Auto7","Auto8","Auto9","Auto10","Auto11","Auto12","DO NOTHING"}; // three different auto positions
	//actuators
	public static WPI_TalonSRX Drive1,Drive2,Drive3,Drive4; //need to use WPI_talonSRX for drivetrain use
	public static MecanumDrive robotdrive; //meccanum drive object
	public static Encoder driveEncoder;
	public static Spark liftmotorL, liftmotorH ,liftBotMotor;
	public static Spark ClampL, ClampR, Dropper;
	//sensors
	public static AHRS ahrs; //AHRS system on navx
	public static Encoder liftEnc;
	public static DigitalInput switchLBase, switchLTop, switchHBase, switchHTop;
	
	//subsystems public static
	public static ExampleSubsystem exampleSystem;
	public static RobotDrive Drive;
	public static RobotVision vision;
	public static Lift lift;
	public static Liftbot liftbot;
	public static CubeManipulation CubeManipulator;
	
	public static void init() {
		//all general objects instantated here
		SmartDashboard.putStringArray("Auto State", autoList); //publishes the auto list to the dashboard "Auto Selector"
		
		//all actuator objects here
		Drive1 = new WPI_TalonSRX(1); 
		Drive2 = new WPI_TalonSRX(2);
		Drive3 = new WPI_TalonSRX(3);
		Drive4 = new WPI_TalonSRX(4);
		robotdrive = new MecanumDrive(Drive1, Drive2, Drive3, Drive4); //create meccanum drive
		driveEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
		liftmotorL = new Spark(1);
		liftmotorH = new Spark(0);
		liftBotMotor = new Spark(5);
		ClampL = new Spark(1);
		ClampR = new Spark(2);
		Dropper = new Spark(3);

		
		//all sensor objects here
		ahrs = new AHRS(SPI.Port.kMXP); //finish declaring AHRS to MXP SPI bus
		ahrs.reset();
		switchLBase = new DigitalInput(0);
		switchLTop = new DigitalInput(1);
		switchHBase = new DigitalInput(2);
		switchHTop = new DigitalInput(3);
		
		//create all subsystem objects
		exampleSystem = new ExampleSubsystem();
		Drive = new RobotDrive();
		vision = new RobotVision();
		liftbot = new Liftbot(); 
		lift = new Lift();
		CubeManipulator = new CubeManipulation();
		
	}
}
