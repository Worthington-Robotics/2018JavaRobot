package org.usfirst.frc.team4145.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;

public class Lift extends Subsystem {

    private boolean limit1 = false;
    private boolean limit2 = false;
    private boolean limit3 = false;
    private boolean limit4 = false;
    private double liftVal = 0.0;
    private boolean[] buttonArray = new boolean[4];
    private int bottomLimit = 0;
    private double stage2Speed = 1.0;

    @Override
    public void initDefaultCommand() {
        RobotMap.liftMotorL.set(0);
        RobotMap.liftMotorH.set(0);

    }

    public void stopliftL() {
        RobotMap.liftMotorL.set(0);

    }

    public void stopliftH() {
        RobotMap.liftMotorH.set(0);
    }

    public void liftspeedH(double n) {
        RobotMap.liftMotorH.set(n);
    }

    public void liftspeedL(double n) {
        RobotMap.liftMotorL.set(n);
    }

    public void stage2Up() {
        RobotMap.liftMotorH.set(-stage2Speed);
    }

    public void stage2Down() {
        RobotMap.liftMotorH.set(stage2Speed);
    }

    public void periodic() {
        updateLimits();
        updateLift();
        watchdog();
    }

    /**
     * get the status of all lift related limit switches
     *
     * @return boolean array of length 4 containing the limit states (0,1 are stage 2 high/low & 2,3 are stage 1 high/low)
     */
    public boolean[] getLimits() {
        return buttonArray;
    }
    
    public void setSpeed(double speed) {
    	liftVal = speed;
    }

    private void updateLimits() {
        limit1 = !RobotMap.switchHBase.get(); //normally open
        limit2 = !RobotMap.switchHTop.get(); //normally open
        limit3 = !RobotMap.switchLTop.get();
        limit4 = !RobotMap.switchLBase.get();
        buttonArray[0] = limit1;
        buttonArray[1] = limit2;
        buttonArray[2] = limit3;
        buttonArray[3] = limit4;
    }

    private void watchdog() {
        if (limit1 && RobotMap.liftMotorH.get() < 0) {
            stopliftH();
        }
        if (limit2 && RobotMap.liftMotorH.get() > 0) {
            stopliftH();
        }
        if (limit3 && RobotMap.liftMotorL.get() > 0) {
            stopliftL();
        }
        if (limit4 && RobotMap.liftEnc.get() <= bottomLimit) {
            stopliftL();
        }
    }

    private void updateLift() {
        if (!DriverStation.getInstance().isAutonomous()) {
            liftVal = evalDeadBand(Robot.oi.getSecondStick().getY(), 0.15);
        }
        liftspeedL(liftVal);

    }

    private double evalDeadBand(double stickInpt, double deadBand) {
        if (Math.abs(stickInpt) < deadBand) {
            return 0;
        } else {
            if (stickInpt < 0) {
                return (0 - Math.pow(stickInpt, 2));
            } else {
                return Math.pow(stickInpt, 2);
            }
        }
    }
}
