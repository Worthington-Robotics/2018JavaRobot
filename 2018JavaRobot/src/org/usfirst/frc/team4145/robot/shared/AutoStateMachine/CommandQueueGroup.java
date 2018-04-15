package org.usfirst.frc.team4145.robot.shared.AutoStateMachine;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.commands.autoonly.FollowPath;

import java.util.LinkedList;

public class CommandQueueGroup {

    private LinkedList<Command> queueGroup;
    private double fpgaStartTime, timeOut;
    private boolean isKillable;

    /**
     * Data structure for storing a series of commands with a timeout
     * @param commands an array of commands
     * @param timeOutMs timeout in milliseconds for the group
     * @param isKillable set if the group can be killed
     */

    CommandQueueGroup(Command[] commands, long timeOutMs, boolean isKillable){
        this.isKillable = isKillable;
        timeOut = timeOutMs / 1000;
        queueGroup = new LinkedList<>();
        for (Command command : commands) {
            queueGroup.add(command);
        }
    }

    /**
     * gets the completion wait of the first command in the state
     * @return wait time in MS before starting next state
     */
    synchronized public int getCompletionWait(){
        return this.getCompletionWait(0);
    }

    /**
     * gets the completion wait of the index command in the state
     * @return wait time in MS before starting next state
     */
    synchronized public int getCompletionWait(int index){
        if(queueGroup.get(index) instanceof FollowPath){
            return ((FollowPath) queueGroup.get(index)).getCompletionWait();
        }
        return 0;
    }

    /**
     * method for checking the status of a queue group
     * @return whether or not the commands have all finished or the timeout was exceeded
     */
    synchronized boolean checkQueueGroup() {
        if((fpgaStartTime + timeOut) <= Timer.getFPGATimestamp()){
            //System.out.println("Queue group timed out");
            return true;
        }
        return checkNoTimeout();
    }

    /**
     * method for checking the status of a queue group
     * @return whether the commands in the state have finished ignoring timeout
     */
    synchronized boolean checkNoTimeout(){
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
    synchronized void startQueueGroup() {
        fpgaStartTime = Timer.getFPGATimestamp();
        for (Command command: queueGroup) {
            command.start();
        }
    }

    /**
     * Does what it says on the tin
     * this method kills the running queue group
     * (if possible to kill)
     */
    synchronized void killQueueGroup() {
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
    synchronized LinkedList getQueueGroup(){
        return queueGroup;
    }

}
