package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class DriveStateWait extends Command{

    @Override
    protected boolean isFinished() {
        return RobotMap.robotDriveV4.isOpenLoop();
    }
}
