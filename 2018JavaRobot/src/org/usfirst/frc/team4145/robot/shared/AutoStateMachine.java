package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.LinkedBlockingQueue;

public class AutoStateMachine {

    private static LinkedBlockingQueue<CommandQueueGroup> blockingQueue;
    private static CommandQueueGroup inspectedElement;
    private static int autoState = 0;
    private static Runnable runnable = () -> {
    	autoState = 0;
        if (blockingQueue != null) {
            System.out.println("State machine size:" + blockingQueue.size());
            SmartDashboard.putString("Auto State Machine Status", "State machine preparing to start!");
            //System.out.println("blocking queue elements:" + blockingQueue.toArray());
            while (blockingQueue.iterator().hasNext()) {
                System.out.println("Entering state " + autoState);
                SmartDashboard.putNumber("Auto State", autoState);
                try {
                    inspectedElement = blockingQueue.take();
                    inspectedElement.startQueueGroup(); //starts queue group running
                    Thread.sleep(40);
                    while (!inspectedElement.checkQueueGroup()) { //checks status of state and whether it is or should be dead
                        //System.out.println("Waiting for previous task to die");
                        Thread.sleep(20);
                    }
                    Thread.sleep(20);
                    while (!inspectedElement.checkQueueGroup()) { //checks status of state and whether it is or should be dead
                        //System.out.println("Waiting for previous task to die");
                        Thread.sleep(20);
                    }
                    SmartDashboard.putNumber("State Advance Flag",1);
                    inspectedElement.killQueueGroup(); //forcefully kills group (just in case)
                    //System.out.println("killing group " + autoState);
                } catch (Exception e) {
                    System.out.println("Failed to sleep or retrieve element");
                }
                //System.out.println("Incrementing state");
                autoState++; //increment auto state
            }

            System.out.println("Finished queue");
            SmartDashboard.putString("Auto State Machine Status", "State machine finished Queue");
        } else {
            System.out.println("State machine list was null!");
            SmartDashboard.putNumber("Auto State", -2);
            SmartDashboard.putString("Auto State Machine Status", "List was NULL!");
        }
    };

    public static void runMachine(LinkedBlockingQueue<CommandQueueGroup> queueGroups) {
        blockingQueue = queueGroups;
        Thread monitor = new Thread(runnable);
        monitor.setDaemon(true);
        monitor.setPriority(8);
        System.out.println("Starting Machine thread now!");
        monitor.start();
    }


}
