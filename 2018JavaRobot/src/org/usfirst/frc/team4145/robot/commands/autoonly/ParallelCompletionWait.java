package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;

public class ParallelCompletionWait extends Command {

    private Command[] commands;

    public ParallelCompletionWait(Command[] commands){
        this.commands = commands;
    }

    @Override
    protected boolean isFinished() {
        boolean isDead = true;
        for (Command command : commands) {
            isDead &= command.isCompleted();
        }
        return isDead;
    }
}
