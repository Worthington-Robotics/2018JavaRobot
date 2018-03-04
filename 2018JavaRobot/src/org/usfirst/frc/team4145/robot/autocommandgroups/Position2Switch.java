package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropCube;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;

public class Position2Switch extends QueueGroup {

    //driving distances
    private int WALL_BREAK = 19*20;
    private int TURN_ONE = 45;
    private int LEFT_DISTANCE = 19*100;
    private int RIGHT_DISTANCE = 19*80;
    private int TURN_TWO = 45;
    private int LEFT_SWITCH_ROLL = 19*28;
    private int RIGHT_SWITCH_ROLL = 19*50;

    //other class variables
    private int LIFT_TO = 0;
    private boolean HARD_SHOT = false;

    public Position2Switch(int autoNum) {
        if(autoNum == 0) {
            //LEFT CODE
            //Drive Forward and lift lift
            addParallel(new Command[]{new DriveTo(WALL_BREAK), new LiftToPosition(LIFT_TO)}, 1500);

            //Turn Right and go forward
            addParallel(new Command[]{new GyroToAngle(-TURN_ONE), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(LEFT_DISTANCE), new LiftToPosition(LIFT_TO)}, 3000);

            //Go towards switch and	drop cube
            addParallel(new Command[]{new GyroToAngle(TURN_TWO), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(LEFT_SWITCH_ROLL), new DropForks(), new LiftToPosition(LIFT_TO)}, 2000);
            addSequential(new DropCube(HARD_SHOT), 1000);
        }
        else{
            //RIGHT CODE
            //Go forward and do fork stuff
            addParallel(new Command[]{new DriveTo(WALL_BREAK), new LiftToPosition(LIFT_TO)}, 1500);

            //Turn Right and go forward
            addParallel(new Command[]{new GyroToAngle(TURN_ONE), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(RIGHT_DISTANCE), new LiftToPosition(LIFT_TO)}, 2500);

            //Go towards switch and	drop cube
            addParallel(new Command[]{new GyroToAngle(-TURN_TWO), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(RIGHT_SWITCH_ROLL), new DropForks(), new LiftToPosition(LIFT_TO)}, 3000);
            addSequential(new DropCube(HARD_SHOT), 1000);


        }
    }
}
