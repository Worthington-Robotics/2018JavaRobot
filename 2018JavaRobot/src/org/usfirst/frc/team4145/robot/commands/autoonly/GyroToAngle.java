package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;

/**
 * this class is for using the gyro lick to a certain angle during auto.
 * when you are ready to release the command call setDoneFlag(true)
 */

public class GyroToAngle extends Command {

    private boolean isDoneFlag = false;
    private double target;

    public GyroToAngle(double target){
        this.target = target;
    }

    public void initialize(){
        RobotMap.Drive.enableTo(target,true);
    }

    public boolean isFinished(){
        return isDoneFlag;
    }

    public void execute(){
        //do nothing the drivetrain automatically handles this
    }

    public void interrupted(){
        end();
    }

    public void end(){
        RobotMap.Drive.enableTo(0,false); //disables gyro lock
    }

    /**
     * sets whether or not the lock is done executing
     * @param flag true to end the lock for a new one
     */
    public void setDoneFlag(boolean flag){
        isDoneFlag = flag;
    }

}
