package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.Robot;
import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
	private boolean Limit1 = false;
	private boolean Limit2 = false;
	private boolean Limit3 = false;
	private boolean Limit4 = false;
	private double Liftval = 0.0;

	@Override
	public void initDefaultCommand() {
		RobotMap.liftmotorL.set(0);
		RobotMap.liftmotorH.set(0);

	}

	public void stopliftL() {
		RobotMap.liftmotorL.set(0);

	}

	public void stopliftH() {
		RobotMap.liftmotorH.set(0);
	}

	public void liftspeedH(double n) {
		RobotMap.liftmotorH.set(n);
	}

	public void liftspeedL(double n) {
		RobotMap.liftmotorL.set(n);
	}

	public void periodic() {
		updateLimits();
		updateLift();
		//watchdog();
	}

	private void updateLimits() {
		Limit1 = RobotMap.switchHBase.get();
		Limit2 = RobotMap.switchHTop.get();
		Limit3 = RobotMap.switchLBase.get();
		Limit4 = RobotMap.switchLTop.get();
	}

	private void watchdog() {
		if (Limit1 && RobotMap.liftmotorH.get() < 0) {
			stopliftH();
		}
		if (Limit2 && RobotMap.liftmotorH.get() > 0) {
			stopliftH();
		}
		if (Limit3 && RobotMap.liftmotorL.get() < 0) {
			stopliftL();
		}
		if (Limit4 && RobotMap.liftmotorL.get() > 0) {
			stopliftL();
		}
	}

	private void updateLift() {
		if (!DriverStation.getInstance().isAutonomous()) {
			Liftval = evalDeadBand(Robot.oi.getSecondStick().getY(), 0.15);
		}
		liftspeedL(Liftval);

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
