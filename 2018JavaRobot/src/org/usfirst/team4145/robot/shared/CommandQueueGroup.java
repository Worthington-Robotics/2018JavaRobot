package org.usfirst.team4145.robot.shared;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class CommandQueueGroup {

    private ArrayList<CommandContainer> queueGroup;
    private boolean isDead = true;

    public CommandQueueGroup(){
        queueGroup = new ArrayList();
    }

    public void addCommandContainer(CommandContainer container){
        requireNonNull(container , "Container cannot be null!");
        queueGroup.add(container);
    }

    public boolean checkCommandStatus(){
        for(CommandContainer container: queueGroup){
            if(container.getTimeout() > (container.getCommand().timeSinceInitialized()*1000)) container.getCommand().cancel();
            isDead &= !container.getCommand().isRunning();
        }
        return isDead;
    }

    public void killQueueGroup(){
        for(CommandContainer container : queueGroup){
            container.getCommand().cancel();
        }
    }
}
