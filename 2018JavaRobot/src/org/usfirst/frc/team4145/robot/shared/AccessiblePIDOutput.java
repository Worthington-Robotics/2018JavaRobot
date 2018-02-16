package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.PIDOutput;

public class AccessiblePIDOutput implements PIDOutput{

	protected double value;
	
	public AccessiblePIDOutput() {
		value = 0.00;
	}

	@Override
	public void pidWrite(double output) {
		value = output;
	}
	
	public double getValue() {
		return value;
	}
	
}
