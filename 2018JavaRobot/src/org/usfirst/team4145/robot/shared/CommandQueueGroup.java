package org.usfirst.team4145.robot.shared;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import java.util.LinkedList;

public class CommandQueueGroup {

    private LinkedList<Command> queueGroup;
    private double FPGA_TIME_AT_START;
    private double TIME_OUT;

    /**
     * Data structure for storing a series of commands with a timeout
     * @param commands an array of commands
     * @param timeOutMs timeout in milliseconds for the group
     */

    public CommandQueueGroup(Command[] commands, long timeOutMs){
        TIME_OUT = timeOutMs / 1000;
        queueGroup = new LinkedList<Command>();
        for (Command command : commands) {
            queueGroup.add(command);
        }
    }

    /**
     * method for checking the status of a queue group
     * @return whether or not the commands have all finished or the timeout was exceeded
     */
    public boolean checkQueueGroup() {
        //System.out.println("Time at queue group start:" + FPGA_TIME_AT_START);
        //System.out.println("Time at check call:"+ Timer.getFPGATimestamp());
        if((FPGA_TIME_AT_START + TIME_OUT) <= Timer.getFPGATimestamp()){
            System.out.println("Queue group timed out");
            return true;
        }
        boolean isDead = true;
        for (Command command : queueGroup) {
            isDead &= !command.isRunning();
            //System.out.println("Command dead?" + command.isRunning());
            
        }
        //System.out.println("Queue group is dead?" + isDead);
        return isDead;
    }

    /**
     * begins running the entire queued group
     * also records FPGA timestamp for timeout purposes.
     */
    public void startQueueGroup() {
        FPGA_TIME_AT_START = Timer.getFPGATimestamp();
        for (Command command: queueGroup) {
            command.start();
        }
    }

    /**
     * Does what it says on the tin
     * this method kills the running queue group.
     */
    public void killQueueGroup() {
        for (Command command : queueGroup) {
            command.cancel();
        }
    }

    /**
     * gets the Linked list for the state machine to run
     * @return a Linked List of all queued groups.
     */
    public LinkedList getQueueGroup(){
        return queueGroup;
    }

}
