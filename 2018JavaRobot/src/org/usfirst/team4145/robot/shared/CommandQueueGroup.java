package org.usfirst.team4145.robot.shared;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class CommandQueueGroup {

    private ArrayList<CommandContainer> queueGroup;
    private boolean isDead = true;

    private volatile boolean monitorStatus = false;

    public Runnable runnable = () -> {
        startQueueGroup();
        while (!checkQueueGroup()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setMonitorStatus(true);
    };

    public CommandQueueGroup() {
        queueGroup = new ArrayList();
    }

    public CommandQueueGroup copyOf() {
        CommandQueueGroup copy = new CommandQueueGroup();
        for (int i = 0; i < queueGroup.size(); i++) {
            copy.addCommandContainer(queueGroup.get(i));
        }
        return copy;
    }

    public void addCommandContainer(CommandContainer container) {
        requireNonNull(container, "Container cannot be null!");
        queueGroup.add(container);
    }

    public void queueGroupForExecution() {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

    private boolean checkQueueGroup() {
        for (CommandContainer container : queueGroup) {
            if (container.getTimeout() > (container.getCommand().timeSinceInitialized() * 1000))
                container.getCommand().cancel();
            isDead &= !container.getCommand().isRunning();
        }
        return isDead;
    }

    private void startQueueGroup() {
        for (CommandContainer container : queueGroup) {
            container.getCommand().start();
        }
    }

    public void killQueueGroup() {
        for (CommandContainer container : queueGroup) {
            container.getCommand().cancel();
        }
    }

    public boolean getMonitorStatus(){
        return monitorStatus;
    }

    private void setMonitorStatus(boolean state) {
        monitorStatus = state;
    }
}
