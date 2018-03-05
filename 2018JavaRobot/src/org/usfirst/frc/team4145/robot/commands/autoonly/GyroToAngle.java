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
    private int multiplier = 5;
    private int timeout;

    @Deprecated
    public GyroToAngle(double addtlRot) {
    	this.addtlRot = addtlRot;
        timeout = (int)Math.abs(addtlRot) * multiplier;
    }

    public void initialize() {
        target = Math.abs((RobotMap.drive.getGyro() + 360 + addtlRot) % 360);
        //System.out.println("Gyro target = " + target);
        //RobotMap.drive.enableTo(target, true);
    }

    public boolean isFinished() {
        //System.out.println("Gyro to angle with timeout of " + timeout + " has completed? " + (cycles < timeout));
        //return cycles > timeout;
    	return false;
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
