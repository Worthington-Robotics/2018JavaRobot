package org.usfirst.frc.team4145.robot.shared.AutoStateMachine;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.FollowPath;

import java.util.LinkedList;

public class CommandQueueGroup {

    private LinkedList<Command> queueGroup;
    private double FPGA_TIME_AT_START;
    private double TIME_OUT;
    private boolean isKillable;

    /**
     * Data structure for storing a series of commands with a timeout
     * @param commands an array of commands
     * @param timeOutMs timeout in milliseconds for the group
     * @param isKillable set if the group can be killed
     */

    CommandQueueGroup(Command[] commands, long timeOutMs, boolean isKillable){
        this.isKillable = isKillable;
        TIME_OUT = timeOutMs / 1000;
        queueGroup = new LinkedList<>();
        for (Command command : commands) {
            queueGroup.add(command);
        }
    }

    public int getCompletionWait(){
        if(queueGroup.peek() instanceof FollowPath){
            return ((FollowPath) queueGroup.peek()).getCompletionWait();
        }
        return 0;
    }

    /**
     * method for checking the status of a queue group
     * @return whether or not the commands have all finished or the timeout was exceeded
     */
    boolean checkQueueGroup() {
        if((FPGA_TIME_AT_START + TIME_OUT) <= Timer.getFPGATimestamp()){
            System.out.println("Queue group timed out");
            return true;
        }
        return checkNoTimeout();
    }

    /**
     * method for checking the status of a queue group
     * @return whether the commands in the state have finished ignoring timeout
     */
    boolean checkNoTimeout(){
        boolean isDead = true;
        for (Command command : queueGroup) {
            isDead &= command.isCompleted();
        }
        return isDead;
    }

    /**
     * begins running the entire queued group
     * also records FPGA timestamp for timeout purposes.
     */
    void startQueueGroup() {
        FPGA_TIME_AT_START = Timer.getFPGATimestamp();
        for (Command command: queueGroup) {
            command.start();
        }
    }

    /**
     * Does what it says on the tin
     * this method kills the running queue group
     * (if possible to kill)
     */
    void killQueueGroup() {
        if(isKillable) {
            for (Command command : queueGroup) {
                command.cancel();
            }
        }
    }

    /**
     * gets the Linked list for the state machine to run
     * @return a Linked List of all queued groups.
     */
    LinkedList getQueueGroup(){
        return queueGroup;
    }

}
