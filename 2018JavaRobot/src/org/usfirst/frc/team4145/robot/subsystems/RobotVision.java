package org.usfirst.frc.team4145.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.team4145.robot.shared.VisionSerial;

/**
 *
 */
public class RobotVision extends Subsystem {

    private VisionSerial vision;
    private Runnable runnable;
    private Thread serialReadThread;
    private volatile double lastRecievedAngle;
    private volatile double lastUsedAngle;


    public RobotVision() {
        vision = new VisionSerial(115200);
        runnable = () -> { //ooh look at the shiny lambda (:
            vision.updateVisionCoordinates();
            //lastCoords = vision.getTargetAngle(vision.getCenter()
        };
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
        if (serialReadThread == null || !serialReadThread.isAlive()) {
            serialReadThread = new Thread(runnable);
            serialReadThread.setDaemon(false);
            //serialReadThread.start();
        }
    }

}


