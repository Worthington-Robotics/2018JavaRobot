package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4145.robot.RobotMap;

/**
 * this class is for using the gyro lick to a certain angle during auto.
 * when you are ready to release the command call setDoneFlag(true)
 */

public class GyroToAngle extends Command {

    private double target;
    private double addtlRot;
    private int cycles = 0;
    private int multiplier = 3;
    private int timeout;

    public GyroToAngle(double addtlRot) {
    	this.addtlRot = addtlRot;
    	SmartDashboard.putNumber("turnTo adjustment", addtlRot);
    	SmartDashboard.putNumber("TurnTo gyro at loadTime", RobotMap.drive.getGyro());
        timeout = (int)Math.abs(target) * multiplier;

    }

    public void initialize() {
        target = (RobotMap.drive.getGyro() + addtlRot) % 360;
        RobotMap.drive.enableTo(target, true);
        SmartDashboard.putNumber("TurnTo gyro", RobotMap.drive.getGyro());
        SmartDashboard.putNumber("new turnTo angle at runtime", target);
    }

    public boolean isFinished() {
        return cycles > timeout;
    }

    public void execute() {
        cycles++;
    }

    public void interrupted() {
        end();
    }

    public void end() {

    }
}
