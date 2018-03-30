package org.usfirst.frc.team4145.robot.shared.AutoStateMachine;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.commands.autoonly.ExecuteMotionProfile;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AutoStateMachine {

    private static ConcurrentLinkedQueue<CommandQueueGroup> stateQueue;
    private static CommandQueueGroup inspectedElement;
    private static int autoState = 0;

    private static Runnable taskRunnable = () -> {
    	autoState = 0;
        if (stateQueue != null) {
            //System.out.println("State machine size:" + stateQueue.size());
            SmartDashboard.putString("Auto State Machine Status", "State machine preparing to start!");
            //System.out.println("blocking queue elements:" + stateQueue.toArray());
            while (!stateQueue.isEmpty()) {
                //System.out.println("Entering state " + autoState + " At time " + RobotController.getFPGATime());
                SmartDashboard.putNumber("Auto State", autoState);
                try {
                    inspectedElement = stateQueue.poll();
                    if(!(inspectedElement.getQueueGroup().peek() instanceof ExecuteMotionProfile) && autoState == 0){ //check to see if first command is motion profile
                        SmartDashboard.putString("Auto State Machine Status", "No motion profile fed in state zero");
                        //System.out.println("State zero did not contain a motion profile");
                    }
                    inspectedElement.startQueueGroup(); //starts queue group running
                    while (!inspectedElement.checkQueueGroup()) { //checks status of state and whether it is or should be dead
                        //System.out.println("Waiting for previous task to die");
                        SmartDashboard.putString("Auto State Machine Status", "Waiting for previous task to die");
                        Timer.delay(0.010);
                    }
                    //SmartDashboard.putNumber("State Advance Flag",1);
                    inspectedElement.killQueueGroup(); //forcefully kills group (just in case)

                    //System.out.println("killing group " + autoState);
                } catch (Exception e) {
                    System.out.println("Failed to sleep or retrieve element");
                }
                //System.out.println("Incrementing state");
                autoState++; //increment auto state
            }

            //System.out.println("Finished queue");
            SmartDashboard.putString("Auto State Machine Status", "State machine finished Queue");
        } else {
            //System.out.println("State machine list was null!");
            SmartDashboard.putNumber("Auto State", -2);
            SmartDashboard.putString("Auto State Machine Status", "List was NULL!");
        }
    };

    public static void runMachine(ConcurrentLinkedQueue<CommandQueueGroup> queueGroups) {
        stateQueue = queueGroups;
        Thread monitor = new Thread(taskRunnable);
        //monitor.setPriority(8);
        System.out.println("Starting Machine thread at time " + RobotController.getFPGATime());
        monitor.start();
    }

}
