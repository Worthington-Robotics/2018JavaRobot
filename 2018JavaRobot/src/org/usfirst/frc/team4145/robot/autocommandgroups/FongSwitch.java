package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.HighLiftUp;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropCube;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class FongSwitch extends QueueGroup {
    public FongSwitch(int autoNum) {
        if(autoNum == 0) {
            //LEFT CODE
            //Drive Forward and do fork stuff
            addParallel(new Command[]{new DriveTo(19 * 10), new LiftToPosition(1000)}, 2000);

            //Turn Right and go forward
            addSequential(new GyroToAngle(-45), 1000);
            addSequential(new DriveTo(19 * 65), 3000);

            //Go towards switch and	drop cube
            addSequential(new GyroToAngle(45), 1000);
            //addSequential(new VisionTarget(), 2000);
            addParallel(new Command[]{new DriveTo(19 * 18), new DropForks()}, 2000);
            addSequential(new DropCube(), 1000);
        }
        else{
            //RIGHT CODE
            //Go forward and do fork stuff
            addParallel(new Command[]{new DriveTo(350), new LiftToPosition(1000)}, 2000);

            //Start going forward towards switch
            addSequential(new GyroToAngle(47), 1000);
            addSequential(new DriveTo(19 * 60), 3000);

            //Go towards switch and drop cube
            addSequential(new GyroToAngle(-47), 1000);
            //addSequential(new VisionTarget(), 1000);
            addParallel(new Command[]{new DriveTo(19 * 20), new DropForks()}, 1500);

            addSequential(new DropCube(), 1000);


        }
    }
}
