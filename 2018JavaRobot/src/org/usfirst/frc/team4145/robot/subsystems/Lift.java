package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
	private boolean Limit1 = false;
	private boolean Limit2 = false;
	private boolean Limit3 = false;
	private boolean Limit4 = false;

	@Override
	public void initDefaultCommand() {
		RobotMap.liftmotorL.set(0);
		RobotMap.liftmotorH.set(0);

	}

	public void liftupL() {
		RobotMap.liftmotorL.set(0.5);

	}

	public void liftupH() {
		RobotMap.liftmotorH.set(0.5);

	}

	public void liftdownL() {
		RobotMap.liftmotorL.set(-0.5);

	}

	public void liftdownH() {
		RobotMap.liftmotorH.set(-0.5);
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
		if (Limit1) {

		}
	}

	private void updateLimits() {
		Limit1 = RobotMap.switchHBase.get();
		Limit2 = RobotMap.switchHTop.get();
		Limit3 = RobotMap.switchLBase.get();
		Limit4 = RobotMap.switchLTop.get();
	}
}
