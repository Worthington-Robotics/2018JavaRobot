package org.usfirst.frc.team4145.robot.shared.StateMachineV2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ParallelCompletionWait extends Command {

    private Command[] commands;
    private static int stateCount = -1;

    public ParallelCompletionWait(Command[] commands){
        this.commands = commands;
    }

    public ParallelCompletionWait(Command command){
        commands = new Command[] {command};
    }

    @Override
    protected boolean isFinished() {
        boolean isDead = true;
        for (Command command : commands) {
            isDead &= command.isCompleted();
        }
        return isDead;
    }

    public void interrupted(){
        end();
    }

    public void end(){
        stateCount++;
    }

    public static void resetCount(){
        stateCount = -1;
    }

    public void updateState(){
        SmartDashboard.putNumber("Auto State", stateCount);
    }
}
