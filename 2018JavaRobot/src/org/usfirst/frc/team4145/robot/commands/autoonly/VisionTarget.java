package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class VisionTarget extends Command {

    double calculatedGyroTarget = 0;

    public VisionTarget() {
        calculatedGyroTarget = RobotMap.vision.calcError();
    }

    public void initialize() {
        RobotMap.Drive.enableTo(calculatedGyroTarget, true);
    }

    public boolean isFinished() {
        return RobotMap.Drive.isOnTarget();
    }

    public void execute() {
        //do nothing the drivetrain automatically handles this
    }

    public void interrupted() {
        end();
    }

    public void end() {
        RobotMap.Drive.enableTo(0, false); //disables gyro lock
    }

}
