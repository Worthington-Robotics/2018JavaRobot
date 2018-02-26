package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropCube;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;

public class FongSwitch extends QueueGroup {
    public FongSwitch(int autoNum) {
        if(autoNum == 0) {
            //LEFT CODE
            //Drive Forward and lift lift
            addParallel(new Command[]{new DriveTo(19 * 12), new LiftToPosition(1000)}, 1500);

            //Turn Right and go forward
            addParallel(new Command[]{new GyroToAngle(-45), new LiftToPosition(1000)}, 1000);
            addParallel(new Command[]{new DriveTo(19 * 85), new LiftToPosition(1000)}, 3000);

            //Go towards switch and	drop cube
            addParallel(new Command[]{new GyroToAngle(45), new LiftToPosition(1000)}, 1000);
            addParallel(new Command[]{new DriveTo(19 * 40), new DropForks(), new LiftToPosition(1000)}, 1900);
            addSequential(new DropCube(false), 1000);
        }
        else{
            //RIGHT CODE
            //Go forward and do fork stuff
            addParallel(new Command[]{new DriveTo(19 * 12), new LiftToPosition(1000)}, 1500);

            //Turn Right and go forward
            addParallel(new Command[]{new GyroToAngle(45), new LiftToPosition(1000)}, 1000);
            addParallel(new Command[]{new DriveTo(19 * 75), new LiftToPosition(1000)}, 3000);

            //Go towards switch and	drop cube
            addParallel(new Command[]{new GyroToAngle(-45), new LiftToPosition(1000)}, 1000);
            addParallel(new Command[]{new DriveTo(19 * 40), new DropForks(), new LiftToPosition(1000)}, 1900);
            addSequential(new DropCube(false), 1000);


        }
    }
}
