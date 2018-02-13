package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.command.Command;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class AutoStateMachine {

    private ArrayList<CommandContainer> commandQue;

    public AutoStateMachine(){
        commandQue = new ArrayList();
    }

    public void addSequential(Command command){
        addSequential(command, -1);
    }

    public void addSequential(Command command, long timeOutMs){
        requireNonNull(command, "Command cannot be null");
        commandQue.add(new CommandContainer(command, false, timeOutMs));
    }

    public void addParallel(Command command){
        addParallel(command, -1);
    }

    public void addParallel(Command command, long timeOutMs){
        requireNonNull(command , "Command cannot be null");
        commandQue.add(new CommandContainer(command, true, timeOutMs));
    }

    

}
