package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team4145.robot.RobotMap;

public class ExecuteMotionProfile extends Command {

    private Trajectory leftInstance, rightInstance;

    public ExecuteMotionProfile(Trajectory left, Trajectory right){
        leftInstance = left; rightInstance = right;
    }

    public void initialize(){
        RobotMap.drive.getAutoDriveInstance().enableToProfile(leftInstance, rightInstance, true);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public void end(){
        RobotMap.drive.getAutoDriveInstance().enableToProfile(leftInstance, rightInstance, false);
    }

    public void interrupted(){
        end();
    }
}
