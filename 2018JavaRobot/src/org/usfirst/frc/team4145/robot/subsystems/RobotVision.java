package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.team4145.robot.shared.VisionNetworkTable;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RobotVision extends Subsystem {

	private VisionNetworkTable vision;

	public RobotVision() {
		vision = new VisionNetworkTable();
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void startVision() {
	}

	public void stopVision() {
	}

	@Override
	public void periodic() {
		vision.updateVisionCoordinates();
	}

}


