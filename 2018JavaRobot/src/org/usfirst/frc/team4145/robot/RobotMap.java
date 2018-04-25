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

    //logging system
    public static LoggingSystem loggingSystem;
    public static String gameDataAtStart;

    // actuators
    public static WPI_TalonSRX driveFrontLeft, driveRearLeft, driveFrontRight, driveRearRight; // need to use WPI_talonSRX for drivetrain use
    public static WPI_TalonSRX liftMotorL, liftMotorH;
    public static Spark liftBotMotor, clampL, clampR, dropper;
    public static DoubleSolenoid liftLock;
    public static Compressor compressor;

    // sensors
    public static AHRS ahrs; // AHRS system on navx
    public static Encoder liftEnc;
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
        liftEnc = Constants.isCompBot()? new Encoder(4, 5, true, Encoder.EncodingType.k4X):
                new Encoder(2,3,false, Encoder.EncodingType.k4X) ;
        switchHTop = new DigitalInput(6);
        switchHBase = new DigitalInput(7);

        // all subsystem objects here
        exampleSystem = new ExampleSubsystem();
        drive = new RobotDriveV3();
        //vision = new RobotVision();
        liftBot = new Liftbot();
        lift = new Lift();
        cubeManipulator = new CubeManipulation();
        addLoggingKeys();
    }

    private static void addLoggingKeys(){
        RobotMap.loggingSystem.addWatchKey("Auto State");
        RobotMap.loggingSystem.addWatchKey("Left Wheel Encoder");
        RobotMap.loggingSystem.addWatchKey("Right Wheel Encoder");
        RobotMap.loggingSystem.addWatchKey("Gyro Angle");
        RobotMap.loggingSystem.addWatchKey("Gyro Target");
        RobotMap.loggingSystem.addWatchKey("Lift Encoder");
        RobotMap.loggingSystem.addWatchKey("Lift Encoder Target");
        RobotMap.loggingSystem.addWatchKey("In Auto");
        RobotMap.loggingSystem.addWatchKey("Left Motor Voltage");
        RobotMap.loggingSystem.addWatchKey("Right Motor Voltage");
        RobotMap.loggingSystem.addWatchKey("Left Talon Voltage");
        RobotMap.loggingSystem.addWatchKey("setpoint1");
        RobotMap.loggingSystem.addWatchKey("feed forward1");
        RobotMap.loggingSystem.addWatchKey("feed back1");
        RobotMap.loggingSystem.addWatchKey("velocity1");
        RobotMap.loggingSystem.addWatchKey("setpoint2");
        RobotMap.loggingSystem.addWatchKey("feed forward2");
        RobotMap.loggingSystem.addWatchKey("feed back2");
        RobotMap.loggingSystem.addWatchKey("velocity2");
        RobotMap.loggingSystem.addWatchKey("error deriv1");
        RobotMap.loggingSystem.addWatchKey("error deriv2");
        RobotMap.loggingSystem.addWatchKey("velocity setpoint1");
        RobotMap.loggingSystem.addWatchKey("velocity setpoint1");
    }
}
