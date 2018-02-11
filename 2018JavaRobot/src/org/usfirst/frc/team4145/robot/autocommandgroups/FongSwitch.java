package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class FongSwitch extends CommandGroup {

    public FongSwitch(int autonumber) {
        if (autonumber == 0) {
            //Go forward and do fork stuff
            addParallel(new DriveTo(19 * 24), 5);
            addSequential(new DropForks());
            //addParallel(new LiftToPosition(500));

            //Start going forward towards switch
            addSequential(new GyroToAngle(47));
            addSequential(new DriveTo(19 * 70), 3);

            //Go towards switch and drop cube
            addSequential(new GyroToAngle(-47));
            //addSequential(new VisionTarget());
            addSequential(new DriveTo(19 * 28),3);
            //addParallel(new GyroToAngle(RobotMap.drive.getGyro()));
            //addSequential(new DropCube());
        }
        if (autonumber == 1) {
            //Drive Forward and do fork stuff
            addParallel(new DriveTo(19 * 50));
            addParallel(new DropForks());
            addParallel(new LiftToPosition(500));
            addParallel(new GyroToAngle(RobotMap.drive.getGyro()));

            //Turn Right and go forward
            addSequential(new GyroToAngle(RobotMap.drive.getGyro() - 90));
            addSequential(new DriveTo(19 * 70));
            addParallel(new GyroToAngle(RobotMap.drive.getGyro()));

            //Go towards switch and	drop cube
            addSequential (new GyroToAngle(RobotMap.drive.getGyro() + 90));
            addSequential(new VisionTarget());
            addSequential(new DriveTo(19 * 70));
            addParallel(new GyroToAngle(RobotMap.drive.getGyro()));
            addSequential(new DropCube());
        }
    }

}