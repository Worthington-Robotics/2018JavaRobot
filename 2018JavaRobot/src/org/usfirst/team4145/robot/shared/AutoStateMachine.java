package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.LinkedBlockingQueue;

public class AutoStateMachine {

    private static LinkedBlockingQueue<CommandQueueGroup> blockingQueue;
    private static CommandQueueGroup inspectedElement;
    private static int autoState = 0;
    private static Runnable runnable = () -> {
        for(int i = 0; i < blockingQueue.size(); i++){
            try {
                inspectedElement = blockingQueue.take();
                inspectedElement.startQueueGroup(); //starts queue group running
                while (!inspectedElement.checkQueueGroup()){ //checks status of state and whether it is or should be dead
                    Thread.sleep(20);
                }
                inspectedElement.killQueueGroup(); //forcefully kills group (just in case)
            } catch (Exception e){
                e.printStackTrace(System.out);
            }
            autoState = i; //increment auto state
            SmartDashboard.putNumber("Auto State", autoState);
        }
    };

    public static void runMachine(LinkedBlockingQueue<CommandQueueGroup> queueGroups) {
        blockingQueue = queueGroups;
        Thread monitor = new Thread(runnable);
        monitor.setDaemon(true);
        monitor.start();
    }


}
