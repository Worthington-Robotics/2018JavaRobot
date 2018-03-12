package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class Position3Scale extends QueueGroup {

    public Position3Scale(int autonumber) {
        //Right
        if (autonumber == 0) {
        	addDrive("/home/lvuser/MotionProfile/Position3Scale0_left_detailed.csv", "/home/lvuser/MotionProfile/Position3Scale1_right_detailed.csv");
            addParallel(new Command[]{new LiftToPosition(Constants.LIFT_MOVE_TO_TOP)},4500);
            addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(Constants.LIFT_MOVE_TO_TOP)}, 1500);
            addParallel(new Command[]{new HighLiftUp(), new LiftToPosition(Constants.LIFT_MOVE_TO_TOP), new DropForks()},1500);
            addSequential(new DropCube(true), 1000);
        }
        //Left
        if (autonumber == 1) {
        	addDrive("/home/lvuser/MotionProfile/Position3Scale1_left_detailed.csv", "/home/lvuser/MotionProfile/Position3Scale1_right_detailed.csv");
            addParallel(new Command[]{new HighLiftUp(), new LiftToPosition(Constants.LIFT_MOVE_TO_TOP)},4000);
            addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(Constants.LIFT_MOVE_TO_TOP)}, 1000);
            addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(Constants.LIFT_MOVE_TO_TOP)}, 2000);
            addParallel(new Command[]{new DropForks(), new HighLiftUp()},1000);
            addSequential(new DropCube(true), 1000);

        }
    }
}
