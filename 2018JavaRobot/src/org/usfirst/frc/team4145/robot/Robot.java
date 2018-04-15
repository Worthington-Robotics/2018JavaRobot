/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.autocommandgroups.testAuto;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.AutoStateMachine;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.CommandQueueGroup;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.RigidTransform2d;
import org.usfirst.frc.team4145.robot.shared.LoggingSystem;
import edu.wpi.first.wpilibj.command.CommandGroup;


import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static OI oi;
    ConcurrentLinkedQueue<CommandQueueGroup> AutoStateQueue;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        oi = new OI();
        SmartDashboard.putNumber("Auto State", -1);
        CameraServer.getInstance().startAutomaticCapture();
        //SmartDashboard.putNumber("Code Revision", 109);
        LoggingSystem.WriteBuildInfoToDashboard();
    }



    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        RobotMap.loggingSystem.enablePrint(false);
        SmartDashboard.putStringArray("Auto List", AutoSelector.buildArray()); // publishes the auto list to the dashboard "Auto Selector"
        RobotMap.robotDriveV4.enableTo(0, false);
        SmartDashboard.putNumber("In Auto", 0);
        SmartDashboard.putNumber("Auto State", -1);
        SmartDashboard.putString("Auto State Machine Status", "State machine not yet started");
        SmartDashboard.putNumber("Lift Encoder Target", 0);
        SmartDashboard.putNumber("Wheel Encoder Target", 0);
        RobotMap.robotDriveV4.setDynamicBrakeMode(true, true, true, true);
        Scheduler.getInstance().removeAll();

    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     * <p>
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        RobotMap.loggingSystem.enablePrint(true);
        RobotMap.robotDriveV4.configAuto();
        RobotMap.robotPose.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        SmartDashboard.putNumber("In Auto", 1);

        String[] autoList = AutoSelector.buildArray();

        //pulls auto selector from labview DB
        String autoSelected = SmartDashboard.getString("Auto Selector", autoList[autoList.length - 1]);

        // this block builds the game data when auto starts
        RobotMap.gameDataAtStart = DriverStation.getInstance().getGameSpecificMessage();

        //choose auto command based on lists
        SmartDashboard.putStringArray("Auto selected and game data", new String[]{autoSelected, RobotMap.gameDataAtStart});
        //AutoStateQueue = AutoSelector.autoSelect(RobotMap.gameDataAtStart, autoSelected);
        //AutoStateQueue = new testAuto(0).getQueuedStates();

        //run state machine
        //AutoStateMachine.runMachine(AutoStateQueue);
        
        CommandGroup testAuto = new testAuto();
        testAuto.start();
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        RobotMap.loggingSystem.enablePrint(false);
        RobotMap.robotDriveV4.configTeleop();
        SmartDashboard.putNumber("In Auto", 0);
        SmartDashboard.putNumber("Auto State", -1);
        RobotMap.robotDriveV4.reset();
        RobotMap.robotDriveV4.enableTo(0, false);

    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testInit(){

    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
