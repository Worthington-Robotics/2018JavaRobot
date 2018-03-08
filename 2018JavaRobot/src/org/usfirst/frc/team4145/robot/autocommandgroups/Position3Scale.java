package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class Position3Scale extends QueueGroup {

    public Position3Scale(int autonumber) {
        //Right
        if (autonumber == 0) {
        	addDrive("Position3Scale0_left.csv", "Position3Scale1_right.csv");
            addParallel(new Command[]{new LiftToPosition(RobotMap.LIFT_TO_SCALE)},4500);
            addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE)}, 1500);
            addParallel(new Command[]{new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE), new DropForks()},1500);
            addSequential(new DropCube(true), 1000);
        }
        //Left
        if (autonumber == 1) {
        	addDrive("Position3Scale1_left.csv", "Position3Scale1_right.csv");
            addParallel(new Command[]{new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE)},4000);
            addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE)}, 1000);
            addParallel(new Command[] {new HighLiftUp(), new LiftToPosition(RobotMap.LIFT_TO_SCALE)}, 2000);
            addParallel(new Command[]{new DropForks(), new HighLiftUp()},1000);
            addSequential(new DropCube(true), 1000);

        }
    }
}
