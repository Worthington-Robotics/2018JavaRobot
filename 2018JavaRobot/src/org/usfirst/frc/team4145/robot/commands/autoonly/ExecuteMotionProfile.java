package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;

public class ExecuteMotionProfile extends Command {

    public ExecuteMotionProfile(){

    }

    public void initialize(){

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public void end(){

    }

    public void interrupted(){
        end();
    }
}
