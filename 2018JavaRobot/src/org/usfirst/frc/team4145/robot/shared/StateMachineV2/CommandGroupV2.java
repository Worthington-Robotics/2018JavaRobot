package org.usfirst.frc.team4145.robot.shared.StateMachineV2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGroupV2 extends CommandGroup{

    public void addSequential(Command command, int timeout){
        addParallel(command, timeout/ 1000.0);
        addSequential(new ParallelCompletionWait(command), timeout/ 1000.0);
    }

    public void addParallel(Command[] commands, int timeout){
        for(Command command : commands){
            addParallel(command, timeout/ 1000.0);
        }
        addSequential(new ParallelCompletionWait( commands), timeout/ 1000.0);
    }

}
