package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.command.Command;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class AutoStateMachine {

    private ArrayList<CommandContainer> toQueue;
    private ArrayList<CommandQueueGroup> queueGroups;
    private int state = 0;
    private Thread monitorThread;
    private Runnable monitor;

    public AutoStateMachine(){
        toQueue = new ArrayList();
        queueGroups = new ArrayList();
    }

    private void buildStates(){
        CommandQueueGroup queueGroup = new CommandQueueGroup();
        for (int i = 0; i < toQueue.size(); i++) {
            if(toQueue.get(i).getIsPara()){
                queueGroup.addCommandContainer(toQueue.get(i));
            }
            else if (i == 0) {
                queueGroup.addCommandContainer(toQueue.get(i));
            }
            else{
                queueGroups.add(queueGroup);
                queueGroup = new CommandQueueGroup();
                queueGroup.addCommandContainer(toQueue.get(i));
            }
        }
    }

    public void runMachine(){
        buildStates();
    }

    public int getState(){
        return state;
    }

    public void addSequential(Command command){
        addSequential(command, -1);
    }

    public void addSequential(Command command, long timeOutMs){
        requireNonNull(command, "Command cannot be null");
        toQueue.add(new CommandContainer(command, false, timeOutMs));
    }

    public void addParallel(Command command){
        addParallel(command, -1);
    }

    public void addParallel(Command command, long timeOutMs){
        requireNonNull(command , "Command cannot be null");
        toQueue.add(new CommandContainer(command, true, timeOutMs));
    }
}
