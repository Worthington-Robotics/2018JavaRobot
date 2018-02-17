package org.usfirst.frc.team4145.robot.commands.teleoponly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;


public class LowGear extends Command {
    public LowGear() {

    }

    @Override
    protected void initialize() {
        RobotMap.drive.setLowGear(true);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    protected void end(){
        RobotMap.drive.setLowGear(false);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
