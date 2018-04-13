package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

import edu.wpi.first.wpilibj.command.Command;

public class Position2Switch extends QueueGroup {

    public Position2Switch(int autoNum) {
        if(autoNum == 0) {
            //LEFT CODE

            addSequential(new LiftToPosition(Constants.LIFT_MOVE_TO_TOP), 2000);
            addSequential(new DropForks(), 1250);
            addSequential(new LiftToPosition(Constants.LIFT_MOVE_TO_TOP), 100);
            addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
            addSequential(new CubeMovement(CubeMovement.CubeState.RollOut), 1000);
        }
        else{
            //RIGHT CODE

            addSequential(new LiftToPosition(Constants.LIFT_MOVE_TO_TOP), 2000);
            addSequential(new DropForks(), 1250);
            addSequential(new LiftToPosition(Constants.LIFT_MOVE_TO_TOP), 100);
            addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
            addSequential(new CubeMovement(CubeMovement.CubeState.RollOut), 1000);
        }
    }
}
