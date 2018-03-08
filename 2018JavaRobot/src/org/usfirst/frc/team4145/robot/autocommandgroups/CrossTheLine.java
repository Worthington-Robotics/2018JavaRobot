package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class CrossTheLine extends QueueGroup {
    public CrossTheLine(){
        addDrive("CrossTheLine_left.csv", "CrossTheLine_right.csv");
    }

}
