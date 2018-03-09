package org.usfirst.frc.team4145.robot.shared.AutoStateMachine;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team4145.robot.shared.PidStuff.VelocitySetpoint;

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
    private Trajectory RightDrive, LeftDrive;

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
    
    protected void addDrive(String leftDrive, String rightDrive) {
        requireNonNull(leftDrive, "left file path cannot be null");
        requireNonNull(rightDrive, "right file path cannot be null");
        LeftDrive = Pathfinder.readFromCSV(new File(leftDrive));
        RightDrive = Pathfinder.readFromCSV(new File(rightDrive));
    }
    
    public Trajectory getRightDrive() {
        return RightDrive;
    }
    
    public Trajectory getLeftDrive() {
        return LeftDrive;
    }
    


    public LinkedBlockingQueue<CommandQueueGroup> getQueuedStates() {
        return (LinkedBlockingQueue<CommandQueueGroup>) queuedStates;
    }
}
