package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class DropCube extends Command{

    private int cycles = 0;
    private int count = 10;

    public DropCube() {
        requires(RobotMap.CubeManipulator);
    }

    @Override
    protected boolean isFinished() {
        return cycles > count;
    }

    public void initialize() {
        RobotMap.CubeManipulator.release();
        // starts motors y'all
    }

    public void execute() {
        cycles++;
    }

    public void end() {
        RobotMap.CubeManipulator.stall();
    }

    public void interrupted() {
        end();
    }

}
