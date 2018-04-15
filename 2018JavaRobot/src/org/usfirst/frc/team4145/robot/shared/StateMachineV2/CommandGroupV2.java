package org.usfirst.frc.team4145.robot.shared.StateMachineV2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4145.robot.commands.autoonly.ParallelCompletionWait;

public class CommandGroupV2 extends CommandGroup{

    public void addSequential(Command command, int timeout){
        addSequential(command, timeout/ 1000.0);
    }

    public void addParallel(Command[] commands, int timeout){
        for(Command command : commands){
            addParallel(command);
        }
        addSequential(new ParallelCompletionWait( commands), timeout/ 1000.0);
    }

}
