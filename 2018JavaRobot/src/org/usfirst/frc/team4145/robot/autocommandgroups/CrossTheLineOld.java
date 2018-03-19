package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;

public class CrossTheLineOld extends QueueGroup {
    public CrossTheLineOld(){
        addSequential(new DriveTo(19 * 102), 1500);
    }

}
