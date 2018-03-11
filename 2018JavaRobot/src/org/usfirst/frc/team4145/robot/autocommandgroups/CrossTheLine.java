package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class CrossTheLine extends QueueGroup {
    public CrossTheLine(){
        addDrive("/home/lvuser/MotionProfile/CrossTheLine_left_detailed.csv", "/home/lvuser/MotionProfile/CrossTheLine_right_detailed.csv");
    }

}
