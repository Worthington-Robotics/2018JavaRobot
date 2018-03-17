package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropCube;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;

public class Position2Switch extends QueueGroup {


    //other class variables
    private int LIFT_TO = 0;
    private boolean HARD_SHOT = true;

    public Position2Switch(int autoNum) {
        if(autoNum == 0) {
            //LEFT CODE
            //Drive Forward and lift lift
        	addDrive("/home/lvuser/MotionProfile/Position2Switch0_left_detailed.csv", "/home/lvuser/MotionProfile/Position2Switch0_right_detailed.csv");
            addParallel(new Command[]{new LiftToPosition(LIFT_TO)}, 2000);
            addParallel(new Command[]{new DropForks(), new LiftToPosition(LIFT_TO)}, 1250);
            addSequential(new LiftToPosition(LIFT_TO), 400);
            addSequential(new DropCube(HARD_SHOT), 1000);
        }
        else{
            //RIGHT CODE
            //Go forward and do fork stuff
        	addDrive("/home/lvuser/MotionProfile/Position2Switch1_left_detailed.csv", "/home/lvuser/MotionProfile/Position2Switch1_right_detailed.csv");
            addParallel(new Command[]{new LiftToPosition(LIFT_TO)}, 2500);
            addParallel(new Command[]{new DropForks(), new LiftToPosition(LIFT_TO)}, 1250);
            addSequential(new DropCube(HARD_SHOT), 1000);


        }
    }
}
