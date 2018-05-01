package org.usfirst.frc.team4145.robot.commands.teleoponly;

        import edu.wpi.first.wpilibj.command.Command;
        import org.usfirst.frc.team4145.robot.RobotMap;

public class EnableLim extends Command {

    public void initialize() {
        RobotMap.liftMotorL.overrideLimitSwitchesEnable(true);
        RobotMap.liftMotorH.overrideLimitSwitchesEnable(true);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}