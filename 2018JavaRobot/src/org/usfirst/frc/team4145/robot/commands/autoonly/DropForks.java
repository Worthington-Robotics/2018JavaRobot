package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4145.robot.RobotMap;

public class DropForks extends Command{

    private int cycles = 0;
    private int complete = 110;

    public DropForks(){

    }
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        RobotMap.cubeManipulator.dropStart();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        SmartDashboard.putNumber("current cycle",cycles++);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return cycles > complete;
        // tells when done
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        RobotMap.cubeManipulator.dropStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
