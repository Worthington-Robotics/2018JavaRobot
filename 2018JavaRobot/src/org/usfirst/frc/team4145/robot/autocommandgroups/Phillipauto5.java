package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class PhillipExchange extends CommandGroup {

    public PhillipExchange(int autonumber) {
        if (autonumber == 1) {
            //Go forward
            addParallel(new DriveTo(19 * 10));
            addParallel(new DropForks());
            addParallel(new GyroToAngle(RobotMap.Drive.getGyro()));
            addSequential(new GyroToAngle(RobotMap.Drive.getGyro()+90));
            addSequential(new DriveTo(19 * 40));
            addSequential(new GyroToAngle(RobotMap.Drive.getGyro()+90));
            addSequential(new DriveTo(19 * 10));
            addSequential(new DropCube());
          if (autonumber == 2) {
             //Go forward
             addParallel(new DriveTo(19 * 10));
             addParallel(new DropForks());
             addParallel(new GyroToAngle(RobotMap.Drive.getGyro()));
             addSequential(new GyroToAngle(RobotMap.Drive.getGyro()-90));
             addSequential(new DriveTo(19 * 50));
             addSequential(new GyroToAngle(RobotMap.Drive.getGyro()-90));
             addSequential(new DriveTo(19 * 10));
                addSequential(new DropCube());
                if (autonumber == 3) {
                    //Go forward
                    addParallel(new DriveTo(19 * 10));
                    addParallel(new DropForks());
                    addParallel(new GyroToAngle(RobotMap.Drive.getGyro()));
                    addSequential(new GyroToAngle(RobotMap.Drive.getGyro()-90));
                    addSequential(new DriveTo(19 * 130));
                    addSequential(new GyroToAngle(RobotMap.Drive.getGyro()-90));
                    addSequential(new DriveTo(19 * 10));
                    addSequential(new DropCube());
        }
    }

