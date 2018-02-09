package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class Release extends Command {
    private boolean isDone = false;

    public Release() {
        requires(RobotMap.CubeManipulator);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    public void initialize() {
        RobotMap.CubeManipulator.fire();
        // starts motors y'all
    }

    public void execute() {

    }

    public void end() {
        RobotMap.CubeManipulator.stall();
    }

    public void interrupted() {
        end();
    }
}
