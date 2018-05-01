package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class DisableLim extends Command {

    public void initialize() {
        RobotMap.liftMotorL.overrideLimitSwitchesEnable(false);
        RobotMap.liftMotorH.overrideLimitSwitchesEnable(false);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}