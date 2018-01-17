package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.team4145.robot.shared.AccessiblePIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RobotDrive extends Subsystem{
private boolean enable = false;
private PIDController GyroLock;
private AccessiblePIDOutput Output;
private double x, y, z;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public RobotDrive() {
		Output = new AccessiblePIDOutput();
		GyroLock = new PIDController(0.1, 0, 0.1, null, Output);
		//PID controller configs and output
	}
	//passes through cartesian parameters. do not use for drive!
	public void Drive(double x, double y, double z) {
		RobotMap.robotdrive.driveCartesian(y, x, z);
		//passes through cartesian parameters
	}
	@Override
	public void initDefaultCommand() {
		this.Drive(0, 0, 0);
	}
	@Override
	//periodic method runs roughly 50 times per second (20 ms)
	public void periodic() {
		if(enable) {
			//Periodically updates while gyro locked
			Drive(x,y,Output.getValue());
		}
		else {
			//periodically updates drive
			Drive(x,y,z);
		}		
	}
	//Sets status of the PID controller
	//also tells the update method to use the gyro lock pid
	public void enableLock(boolean en) {
		enable = en;
		if(enable) {
			GyroLock.enable();// if true enable lock. 
		}
		else {
			GyroLock.disable(); //if boolean is false disable lock
		}
	}
	public void setTarget(double rot) {
		GyroLock.setSetpoint(rot);
	}
	public void setPID(double p, double i, double d) {
		GyroLock.setPID(p, i, d);
	}
	//enable with parameters
	public void enWithParam(double p, double i, double d, double rot) {
		setPID(p,i,d);
		setTarget(rot);
		enableLock(true);
	}//use this method to set joystick values
	public void setXYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
