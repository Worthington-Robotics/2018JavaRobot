package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class CrossLineLong extends QueueGroup {
    public CrossLineLong(){
        addDrive("/home/lvuser/MotionProfile/CrossLineLong_left_detailed.csv","/home/lvuser/MotionProfile/CrossLineLong_right_detailed.csv");
    }
}
