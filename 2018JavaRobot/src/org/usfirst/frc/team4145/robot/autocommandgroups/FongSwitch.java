package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.team4145.robot.shared.QueueGroup;


public class FongSwitch extends QueueGroup {

    public FongSwitch(int autonumber) {
        if (autonumber == 0) {
            //Go forward and do fork stuff
            addParallel(new Command[]{new DriveTo(19 * 24), new DropForks()}, 1500);
            //addParallel(new LiftToPosition(500));

            //Start going forward towards switch
            addSequential(new GyroToAngle(47), 1500);
            addSequential(new DriveTo(19 * 70), 2000);

            //Go towards switch and drop cube
            addSequential(new GyroToAngle(-47), 1500);
            //addSequential(new VisionTarget(), 1000);
            addSequential(new DriveTo(19 * 28), 1000);

            addSequential(new DropCube(), 750);
        }
        if (autonumber == 1) {
            //Drive Forward and do fork stuff
            addParallel(new Command[]{new DriveTo(19 * 50), new DropForks(),
                    new LiftToPosition(500)}, 1500);

            //Turn Right and go forward
            addSequential(new GyroToAngle(-90), 2000);
            addSequential(new DriveTo(19 * 70), 2000);

            //Go towards switch and	drop cube
            addSequential(new GyroToAngle(90), 2000);
            addSequential(new VisionTarget(), 2000);
            addSequential(new DriveTo(19 * 70), 2000);
            addSequential(new DropCube(), 200);
        }
    }

}