package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.RobotMap;

public class VisionSerial {

    private static final byte[] ON_BYTE = new byte[1];
    private static final byte[] OFF_BYTE = new byte[1];
    private static final byte[] HEARTBEAT_BYTE = new byte[1];
    private static final double PIXEL_TARGET = 160.0;
    private static final double PIXEL_PER_DEGREE = 6.62;
    private double centerCoordinate = 0.0;
    private String centerString = "";
    private SerialPort serialPort;

    public VisionSerial(int baudRate) {
        serialPort = new SerialPort(baudRate, Port.kMXP);
        serialPort.reset();
        serialPort.setTimeout(3);
        serialPort.flush();
    }

    public void sendOn() {
        // Write 'on' to Pi/Jetson
        serialPort.write(ON_BYTE, 1);
        serialPort.flush();
    }

    public void heartbeat() {
        // Write Heartbeat to Pi/Jetson
        serialPort.write(HEARTBEAT_BYTE, 1);
        serialPort.flush();
    }

    public void updateVisionCoordinates() {
        // Read from Pi/Jetson over serial
        try {
            centerString = serialPort.readString(6);
        } catch (Exception e) {
            centerString = "-2"; //catches a non-connected port error
        }

        // Reset centerCoordinate
        centerCoordinate = 0.0;
        try {
            // Try to convert to a decimal
            centerCoordinate = Double.valueOf(centerString);
        } catch (NumberFormatException e) {
            // Error decoding while reading from Serial
            centerCoordinate = -1.0;
        }

        SmartDashboard.putNumber("Vision Error", centerCoordinate);
        //System.out.println("Center = " + centerCoordinate + " \'" + centerString + "\'");
    }

    public double getCenter() {
        return centerCoordinate;
    }

    public void off() {
        // Turns off Pi/Jetson over serial
        serialPort.write(OFF_BYTE, 1);
    }

    public double getTargetAngle(double currentCenter) {
        double pixelOffset = currentCenter - PIXEL_TARGET;
        double angleOffset = pixelOffset / PIXEL_PER_DEGREE;
        double gyroValue = RobotMap.drive.getGyro() + angleOffset;
        return gyroValue;
    }

    public void flush() {
        serialPort.flush();
    }

}