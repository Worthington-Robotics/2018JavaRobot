package org.usfirst.frc.team4145.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;

public class Lift extends Subsystem implements PIDSource, PIDOutput{

    private boolean triggerLastCycle = false;
    private double liftVal = 0.0;
    private boolean[] buttonArray = new boolean[4];
    private int newCount;
    private PIDController liftPid;

    public Lift(){
        RobotMap.liftEnc.reset();
        liftPid = new PIDController(Constants.getLifttoKp(), Constants.getLifttoKi(), Constants.getLifttoKd(), this, this::pidWrite); // create pid object with parameters
        liftPid.setOutputRange(-Constants.getLifttoLim(), Constants.getLifttoLim());
        liftPid.setAbsoluteTolerance(Constants.getLifttoTol()); // set tolerance of pid
    }

    public double pidGet() {
        return RobotMap.liftEnc.get();
    }

    public void pidWrite(double set) {
        //System.out.println("Liftpid set to: " + set);
        liftVal = set;
    }


    /**
     * get the status of all lift related limit switches
     *
     * @return boolean array of length 4 containing the limit states (0,1 are stage 2 high/low & 2,3 are stage 1 high/low)
     */
    public boolean[] getLimits() {
        return buttonArray;
    }

    /**
     * method used to override values sent to the lift
     * @param speed of lower lift stage
     */
    public void setSpeed(double speed) {
        liftVal = speed;
    }

    public void stopliftH() {
        RobotMap.liftMotorH.set(0);
    }

    public void stage2Up() {
        RobotMap.liftMotorH.set(-Constants.getStage2ButtonSpeed());
    }

    public void stage2Down() {
        RobotMap.liftMotorH.set(Constants.getStage2ButtonSpeed());
    }

    /**
     * enables lift PID controller
     * @param target encoder value - if disabling value is ignored
     * @param enable whether to enable or disable the controller
     */
    public void enableTo(int target, boolean enable){
        if(enable){
            newCount = target;
            liftPid.setSetpoint(newCount);
            liftPid.enable();
        }
        else liftPid.disable();
    }

    public void periodic() {
        updateLimits();
        updateLift();
        runPrints();
    }
    
    private void runPrints() {
    	SmartDashboard.putNumber("Lift Encoder", RobotMap.liftEnc.get());
        SmartDashboard.putNumber("Lift Encoder Target", newCount);
    	SmartDashboard.putBoolean("Stage 1 Low", buttonArray[3]);
    	SmartDashboard.putBoolean("Stage 1 High", buttonArray[2]);
    	SmartDashboard.putBoolean("Stage 2 Low", buttonArray[0]);
    	SmartDashboard.putBoolean("Stage 2 High", buttonArray[1]);
    }

    private void updateLimits() {
        buttonArray[0] = RobotMap.liftMotorH.getSensorCollection().isFwdLimitSwitchClosed();
        buttonArray[1] = RobotMap.liftMotorH.getSensorCollection().isRevLimitSwitchClosed();
        buttonArray[2] = RobotMap.liftMotorL.getSensorCollection().isFwdLimitSwitchClosed();
        buttonArray[3] = RobotMap.liftMotorL.getSensorCollection().isRevLimitSwitchClosed();
    }


    private void updateLift() {
        if (!liftPid.isEnabled()) liftPid.setSetpoint(RobotMap.liftEnc.get());
        if (!DriverStation.getInstance().isAutonomous()) {
            liftVal = evalDeadBand(Robot.oi.getSecondStick().getY(), 0.15);
            if (Robot.oi.getSecondStick().getRawButton(1) || LiftDisable()) { //get trigger status
                RobotMap.liftMotorH.set(-liftVal * Constants.getStage2Multiplier()); //if pressed run lift directly
                triggerLastCycle = true;
            } else if (triggerLastCycle) {
                RobotMap.liftMotorH.set(0);
                triggerLastCycle = false;
            }
        }
        RobotMap.liftMotorL.set(liftVal);
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
    
    private boolean LiftDisable()
    {
    		return ((buttonArray[2] && !buttonArray[1]) || (buttonArray[3] && !buttonArray[0])) && !SmartDashboard.getBoolean("DB/Button 0", false);
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {

    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public void initDefaultCommand() {
    }
}
