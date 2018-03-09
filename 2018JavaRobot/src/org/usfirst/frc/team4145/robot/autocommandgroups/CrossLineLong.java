package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class CrossLineLong extends QueueGroup {
    public CrossLineLong(){
        addDrive("CrossLineLong_left.csv","CrossLineLong_right.csv");
    }
}
