package org.usfirst.frc.team4145.robot.shared.AutoStateMachine;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team4145.robot.commands.autoonly.ExecuteMotionProfile;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Objects.requireNonNull;


/**
 * An extendable class that kicks out a Linked list of commandQueueGroups
 * this replaces the command group class
 */
public class QueueGroup {

    private Queue<CommandQueueGroup> queuedStates;
    private Trajectory m_RightDrive, m_LeftDrive;

    public QueueGroup() {
        queuedStates = new LinkedBlockingQueue<>();
    }


    protected void addSequential(Command command, long timeOutMs) {
        requireNonNull(command, "Command cannot be null");
        queuedStates.add(new CommandQueueGroup(new Command[]{command}, timeOutMs, true));
    }

    protected void addParallel(Command[] commands, long timeOutMs) {
        requireNonNull(commands, "Command cannot be null");
        queuedStates.add(new CommandQueueGroup(commands, timeOutMs, true));
    }
    
    protected void addDrive(String leftDrive, String rightDrive) {
        requireNonNull(leftDrive, "left file path cannot be null");
        requireNonNull(rightDrive, "right file path cannot be null");
        m_LeftDrive = Pathfinder.readFromCSV(new File(leftDrive));
        m_RightDrive = Pathfinder.readFromCSV(new File(rightDrive));
        queuedStates.add(new CommandQueueGroup(new Command[] {new ExecuteMotionProfile(m_LeftDrive, m_RightDrive)}, 50, false));
    }

    public LinkedBlockingQueue<CommandQueueGroup> getQueuedStates() {
        return (LinkedBlockingQueue<CommandQueueGroup>) queuedStates;
    }
}
