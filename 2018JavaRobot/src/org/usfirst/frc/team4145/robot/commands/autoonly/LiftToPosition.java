package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.PIDOutput;
import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftToPosition extends Command implements PIDSource, PIDOutput {

	private int newCount;
	private PIDController liftPid;

	private int TOLERANCE = 1;
	private int AUTHORITY = 1;
	private double kP = 0.025;
	private double kI = 0.000;
	private double kD = 0.005;

	public LiftToPosition(int count) { //
		requires(RobotMap.lift);
		newCount = count;
		RobotMap.liftEnc.reset();
		liftPid = new PIDController(kP, kI, kD, this, this::pidWrite); // create pid object with parameters
		liftPid.setOutputRange(-AUTHORITY, AUTHORITY);
		liftPid.setAbsoluteTolerance(TOLERANCE); // set tolerance of pid
	}

	public double pidGet() {
		return -RobotMap.liftEnc.get();
	}

	public void pidWrite(double set) {
		RobotMap.lift.setSpeed(set);
	}

	public void initialize() {
		//System.out.println("Starting LiftPID");
        SmartDashboard.putNumber("Lift Encoder Target", newCount);
		liftPid.setSetpoint(newCount); // set target of pid
		liftPid.enable(); // start pid

	}

	public void execute() {
	}

	public boolean isFinished() {
		return liftPid.onTarget(); // If pid is at tolerance

	}

	public void end() {
		liftPid.disable();// Stop Pid
		liftPid.free(); // Frees sensor and actuator
		// If pid is at tolerance
	}

	public void interrupted() { //
		end();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}
}