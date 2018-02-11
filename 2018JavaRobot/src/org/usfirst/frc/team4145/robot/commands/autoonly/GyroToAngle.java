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
    private int cycles = 0;
    private int multiplier = 2;
    private int timeout;

    public GyroToAngle(double addtlRot) {
        target = (RobotMap.drive.getGyro() + addtlRot) % 360;
        timeout = (int)Math.abs(target) * multiplier;

    }

    public void initialize() {
        RobotMap.drive.enableTo(target, true);
        System.out.println("Sucessfully set gyro Target to:" + target);
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
