/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team4145.robot.shared.LoggingSystem;
import org.usfirst.frc.team4145.robot.subsystems.*;
import org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3.RobotDriveV3;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    //close scale variables
    public static int CLOSE_SCALE_DISTANCE = 19 * 265;
    public static double CLOSE_SCALE_TURN = -50;
    public static int CLOSE_SCALE_ROLL = 19*0;
    public static int LIFT_TO_SCALE = 200;

    //far scale variables
    public static int FAR_SCALE_DISTANCE_1 = 19 * 220;
    public static double FAR_SCALE_TURN_1 = 85;
    public static int FAR_SCALE_DISTANCE_2 = 19*225;
    public static double FAR_SCALE_TURN_2 = -107;
    public static int FAR_SCALE_DISTANCE_3 = 19*40; //was 75

    //logging system
    public static LoggingSystem loggingSystem;

    // actuators
    public static WPI_TalonSRX driveFrontLeft, driveRearLeft, driveFrontRight, driveRearRight; // need to use WPI_talonSRX for drivetrain use
    public static WPI_TalonSRX liftMotorL, liftMotorH;
    public static Spark liftBotMotor, clampL, clampR, dropper;
    public static DoubleSolenoid liftLock;
    public static Compressor compressor;

    // sensors
    public static AHRS ahrs; // AHRS system on navx
    public static Encoder rightWheelEncoder, leftWheelEncoder, liftEnc;
    public static DigitalInput switchHBase, switchHTop, botHighSw, botLowSw;

    // subsystems public static
    public static ExampleSubsystem exampleSystem;
    public static RobotDriveV3 drive;
    public static Lift lift;
    public static Liftbot liftBot;
    public static CubeManipulation cubeManipulator;

    public static void init() {
        // all general objects instantated here
        loggingSystem = new LoggingSystem();

        // all Motor controller objects
        driveFrontLeft = new WPI_TalonSRX(1);
        driveRearLeft = new WPI_TalonSRX(2);
        driveFrontRight = new WPI_TalonSRX(3);
        driveRearRight = new WPI_TalonSRX(4);
        liftMotorH = new WPI_TalonSRX(7);
        liftMotorL = new WPI_TalonSRX(6);
        clampL = new Spark(2);
        clampR = new Spark(3);
        dropper = new Spark(4);
        liftBotMotor = new Spark(5);

        //all compressor objects here
        compressor = new Compressor(5);
        
        // all solenoid objects here
        liftLock = new DoubleSolenoid(5,0,1);

        // all sensor objects here
        ahrs = new AHRS(SPI.Port.kMXP); // finish declaring AHRS to MXP SPI bus
        ahrs.reset();
        rightWheelEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
        leftWheelEncoder = new Encoder(2,3,false, Encoder.EncodingType.k4X);
        liftEnc = new Encoder(4, 5, true, Encoder.EncodingType.k4X);
        switchHTop = new DigitalInput(6);
        switchHBase = new DigitalInput(7);

        // all subsystem objects here
        exampleSystem = new ExampleSubsystem();
        drive = new RobotDriveV3();
        //vision = new RobotVision();
        liftBot = new Liftbot();
        lift = new Lift();
        cubeManipulator = new CubeManipulation();

    }
}
