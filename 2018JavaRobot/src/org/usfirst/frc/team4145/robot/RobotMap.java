/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.subsystems.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final String autoList[] = {"Auto1", "Auto2", "Auto3", "Auto4", "Auto5", "Auto6", "Auto7", "Auto8",
            "Auto9", "Auto10", "Auto11", "Auto12", "DO NOTHING"}; // three different auto positions
    // public variables
    public static String GameData = null; // field alliance data about switch / scale
    // actuators
    public static WPI_TalonSRX Drive1, Drive2, Drive3, Drive4; // need to use WPI_talonSRX for drivetrain use
    public static MecanumDrive robotdrive; // meccanum drive object
    public static Encoder driveEncoder;
    public static Spark liftmotorL, liftmotorH, liftBotMotor;
    public static Spark ClampL, ClampR, Dropper;
    public static Solenoid liftLock;

    // sensors
    public static AHRS ahrs; // AHRS system on navx
    public static Encoder liftEnc;
    public static DigitalInput switchLBase, switchLTop, switchHBase, switchHTop;
    public static DigitalInput botHighSw, botLowSw;

    // subsystems public static
    public static ExampleSubsystem exampleSystem;
    public static RobotDriveV2 Drive;
    public static RobotVision vision;
    public static Lift lift;
    public static Liftbot liftbot;
    public static CubeManipulation CubeManipulator;

    public static void init() {
        // all general objects instantated here


        // all actuator objects here
        Drive1 = new WPI_TalonSRX(1);
        Drive2 = new WPI_TalonSRX(2);
        Drive3 = new WPI_TalonSRX(3);
        Drive4 = new WPI_TalonSRX(4);
        robotdrive = new MecanumDrive(Drive1, Drive2, Drive3, Drive4); // create meccanum drive
        liftmotorL = new Spark(1);
        liftmotorH = new Spark(0);
        liftBotMotor = new Spark(5);
        ClampL = new Spark(2);
        ClampR = new Spark(3);
        Dropper = new Spark(4);
        liftLock = new Solenoid(1);

        // all sensor objects here
        ahrs = new AHRS(SPI.Port.kMXP); // finish declaring AHRS to MXP SPI bus
        ahrs.reset();
        driveEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
        liftEnc = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
        switchLBase = new DigitalInput(4);
        switchLTop = new DigitalInput(5);
        switchHBase = new DigitalInput(6);
        switchHTop = new DigitalInput(7);
        botHighSw = new DigitalInput(8);
        botLowSw = new DigitalInput(9);

        // create all subsystem objects
        exampleSystem = new ExampleSubsystem();
        Drive = new RobotDriveV2();
        vision = new RobotVision();
        liftbot = new Liftbot();
        lift = new Lift();
        CubeManipulator = new CubeManipulation();

    }
}
