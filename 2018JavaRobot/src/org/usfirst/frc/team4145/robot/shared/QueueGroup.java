package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.command.Command;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Objects.requireNonNull;


/**
 * An extendable class that kicks out a Linked list of commandQueueGroups
 * this replaces the command group class
 */
public class QueueGroup {

    private Queue<CommandQueueGroup> queuedStates;

    public QueueGroup() {
        queuedStates = new LinkedBlockingQueue<>();
    }


    protected void addSequential(Command command, long timeOutMs) {
        requireNonNull(command, "Command cannot be null");
        queuedStates.add(new CommandQueueGroup(new Command[]{command}, timeOutMs));
    }

    protected void addParallel(Command[] commands, long timeOutMs) {
        requireNonNull(commands, "Command cannot be null");
        queuedStates.add(new CommandQueueGroup(commands, timeOutMs));
    }

    public LinkedBlockingQueue<CommandQueueGroup> getQueuedStates() {
        return (LinkedBlockingQueue<CommandQueueGroup>) queuedStates;
    }
}
