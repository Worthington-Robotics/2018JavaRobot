package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class PickCube extends Command{

    private int cycles = 0;
    private int count = 10;

    public PickCube() {
        requires(RobotMap.cubeManipulator);
    }

    @Override
    protected boolean isFinished() {
        return cycles > count;
    }

    public void initialize() {
        RobotMap.cubeManipulator.pickup();
        // starts motors y'all
    }

    public void execute() {
        cycles++;
    }

    public void end() {
        RobotMap.cubeManipulator.stall();
    }

    public void interrupted() {
        end();
    }

}
