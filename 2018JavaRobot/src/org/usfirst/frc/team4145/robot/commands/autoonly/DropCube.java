package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class DropCube extends Command{

    private int cycles = 0;
    private int count = 100;
    private boolean shoot;

    public DropCube(boolean shoot) {
        requires(RobotMap.cubeManipulator);
        this.shoot = shoot;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public void initialize() {
        // starts motors y'all
        if(shoot){
            RobotMap.cubeManipulator.fire();
        }
        else {
            RobotMap.cubeManipulator.release();
        }
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
