package org.usfirst.frc.team4145.robot.subsystems;

import org.usfirst.team4145.robot.shared.VisionSerial;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RobotVision extends Subsystem {

	private VisionSerial vision;

	public RobotVision() {
		vision = new VisionSerial(115200);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void startVision() {
		vision.sendOn();
	}

	public void stopVision() {
		vision.off();
	}

	@Override
	public void periodic() {
		//vision.heartbeat();
		//vision.updateVisionCoordinates();
		//vision.getCoords();
	}

}


