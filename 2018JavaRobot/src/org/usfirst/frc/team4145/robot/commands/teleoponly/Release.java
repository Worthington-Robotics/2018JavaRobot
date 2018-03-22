package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class Release extends Command {
    private boolean isDone = false;

    public Release() {
        requires(RobotMap.cubeManipulator);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    public void initialize() {
        RobotMap.cubeManipulator.fire();
        // starts motors y'all
    }

    public void execute() {

    }

    public void end() {
        RobotMap.cubeManipulator.stall();
    }

    public void interrupted() {
        end();
    }
}
