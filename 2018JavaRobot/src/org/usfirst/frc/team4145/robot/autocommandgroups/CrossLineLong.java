package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.shared.QueueGroup;

public class CrossLineLong extends QueueGroup {
    public CrossLineLong(){
        addSequential(new DriveTo(19 * 204), 10000);
    }
}
