package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

public class HighLiftUp extends Command {

    private int cycles = 0; //DO NOT CHANGE
    private int timeout = 500; //timeout on command Nominal: 50 cycles (1 second)


    public HighLiftUp() {

    }

    public void initialize() {
        RobotMap.lift.stage2Up();
        System.out.println("Starting high lift up");
    }

    public void execute() {
        cycles++;
    }

    public boolean isFinished() {
        return false; //RobotMap.lift.getLimits()[1]; //|| cycles > timeout;
    }

    public void end() {
        RobotMap.lift.stopliftH();
        System.out.println("Stopping Lift");
    }

    public void interrupted() {
        end();
    }
}
