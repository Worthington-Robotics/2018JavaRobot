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
    private volatile double lastReceivedCenter;

    private int baudRate = 115200; //serial baudrate Nominal: 115200

    public RobotVision() {
        vision = new VisionSerial(baudRate);
        runnable = () -> { //ooh look at the shiny lambda (:
            vision.updateVisionCoordinates();
            lastReceivedCenter = vision.getCenter();
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
        return vision.getTargetAngle(lastReceivedCenter);
    }

    public void flush() {
        vision.flush();
    }

}


