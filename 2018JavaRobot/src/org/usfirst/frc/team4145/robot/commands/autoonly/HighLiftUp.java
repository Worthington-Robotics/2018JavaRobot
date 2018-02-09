package org.usfirst.frc.team4145.robot.commands.autoonly;

import org.usfirst.frc.team4145.robot.RobotMap;

public class HighLiftUp {

    private int cycles = 0; //DO NOT CHANGE
    private int timeout = 50; //timeout on command Nominal: 50 cycles (1 second)
    private double liftSpeed = 0.5; //sets speed of lift Nominal: 0.5


    public HighLiftUp() {

    }

    public void initialize() {
        RobotMap.lift.liftspeedH(liftSpeed);
    }

    public void execute() {
        cycles++;
    }

    public boolean isFinished() {
        return RobotMap.lift.getLimits()[0] || cycles > timeout;
    }

    public void end() {
        RobotMap.lift.stopliftH();
    }

    public void interrupted() {
        end();
    }
}
