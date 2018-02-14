package org.usfirst.team4145.robot.shared;

import java.util.Queue;

public class AutoStateMachineV2 {

    private static Queue<CommandQueueGroup> queueGroups;

    public static void executeList(Queue<CommandQueueGroup> queueGroupsLocal){
        queueGroups = queueGroupsLocal;

    }

    private Runnable runnable = () -> {
      for(CommandQueueGroup queueGroup : queueGroups){
          queueGroup.queueGroupForExecution();
          while ()
      }
    };

}
