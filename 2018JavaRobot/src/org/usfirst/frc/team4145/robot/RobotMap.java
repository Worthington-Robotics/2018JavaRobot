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
import org.usfirst.frc.team4145.robot.shared.LoggingSystem;
import org.usfirst.frc.team4145.robot.subsystems.*;
import org.usfirst.frc.team4145.robot.subsystems.RobotDriveV4.PoseEstimator;
import org.usfirst.frc.team4145.robot.subsystems.RobotDriveV4.RobotDriveV4;

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


    // subsystems public static
    public static ExampleSubsystem exampleSystem;
    public static RobotDriveV4 robotDriveV4;
    public static PoseEstimator robotPose;
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
        liftEnc = Constants.isCompBot()? new Encoder(4, 5, true, Encoder.EncodingType.k4X):
                new Encoder(2,3,true, Encoder.EncodingType.k4X) ;

        // all subsystem objects here
        exampleSystem = new ExampleSubsystem();
        robotDriveV4 = new RobotDriveV4();
        robotDriveV4.reset();
        robotPose = new PoseEstimator();
        liftBot = new Liftbot();
        lift = new Lift();
        cubeManipulator = new CubeManipulation();
        addLoggingKeys();
    }

    private static void addLoggingKeys(){
        loggingSystem.addWatchKey("Auto State");
        loggingSystem.addWatchKey("Left Wheel Encoder");
        loggingSystem.addWatchKey("Right Wheel Encoder");
        loggingSystem.addWatchKey("Gyro Angle");
        loggingSystem.addWatchKey("Gyro Target");
        loggingSystem.addWatchKey("Lift Encoder");
        loggingSystem.addWatchKey("Lift Encoder Target");
        loggingSystem.addWatchKey("In Auto");
        loggingSystem.addWatchKey("Right Wheel Velocity");
        loggingSystem.addWatchKey("Left Wheel Velocity");
        loggingSystem.addWatchKey("Robot Pose X");
        loggingSystem.addWatchKey("Robot Pose Y");
        loggingSystem.addWatchKey("Robot Pose Theta");
    }
}
