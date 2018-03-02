package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class CrossTheLine extends QueueGroup {
    public CrossTheLine(){
        addSequential(new DriveTo(19 * 102), 1500);
    }

}
