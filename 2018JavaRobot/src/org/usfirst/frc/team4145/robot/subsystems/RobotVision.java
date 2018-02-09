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
    private volatile double lastRecievedCenter;
    private volatile double lastUsedCenter;

    private int tolerance = 3; //minimum value necesstating a gyro turn Nominal:3


    public RobotVision() {
        vision = new VisionSerial(115200);
        runnable = () -> { //ooh look at the shiny lambda (:
            vision.updateVisionCoordinates();
            lastRecievedCenter = vision.getCenter();
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
            serialReadThread.setDaemon(true);
            serialReadThread.start();
        }
    }

    public double calcError() {
        if (lastRecievedCenter < lastUsedCenter - tolerance || lastRecievedCenter > lastUsedCenter + tolerance) {
            lastUsedCenter = lastRecievedCenter;
            return vision.getTargetAngle(lastRecievedCenter);
        }
        return vision.getTargetAngle(lastUsedCenter);
    }

    public void flush() {
        vision.flush();
    }

}


