package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class Position2SwitchOld extends QueueGroup {


    //other class variables
    private int LIFT_TO = 0;

    public Position2SwitchOld(int autoNum) {
        if(autoNum == 0) {
            //LEFT CODE
            //Drive Forward and lift lift
            addParallel(new Command[]{new DriveTo(Constants.POS2SWITCH_WALL_BREAK), new LiftToPosition(LIFT_TO)}, 1500);

            //Turn Right and go forward
            addParallel(new Command[]{new GyroToAngle(-Constants.POS2SWITCH_TURN_ONE), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(Constants.POS2SWITCH_LEFT_DISTANCE), new LiftToPosition(LIFT_TO)}, 3000);

            //Go towards switch and	drop cube
            addParallel(new Command[]{new GyroToAngle(Constants.POS2SWITCH_TURN_TWO), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(Constants.POS2SWITCH_LEFT_SWITCH_ROLL), new DropForks(), new LiftToPosition(LIFT_TO)}, 2000);
            addSequential(new CubeMovement(CubeMovement.CubeState.RollOut), 1000);
        }
        else{
            //RIGHT CODE
            //Go forward and do fork stuff
            addParallel(new Command[]{new DriveTo(Constants.POS2SWITCH_WALL_BREAK), new LiftToPosition(LIFT_TO)}, 1500);

            //Turn Right and go forward
            addParallel(new Command[]{new GyroToAngle(Constants.POS2SWITCH_TURN_ONE), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(Constants.POS2SWITCH_RIGHT_DISTANCE), new LiftToPosition(LIFT_TO)}, 2500);

            //Go towards switch and	drop cube
            addParallel(new Command[]{new GyroToAngle(-Constants.POS2SWITCH_TURN_TWO), new LiftToPosition(LIFT_TO)}, 1000);
            addParallel(new Command[]{new DriveTo(Constants.POS2SWITCH_RIGHT_SWITCH_ROLL), new DropForks(), new LiftToPosition(LIFT_TO)}, 3000);
            addSequential(new CubeMovement(CubeMovement.CubeState.RollOut), 1000);
        }
    }
}
