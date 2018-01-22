package org.usfirst.frc.team4145.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RobotVision extends Subsystem {

    private SerialPort serialPort;

    private int baudRate = 9600;

    private static final byte[] ON_BYTE = new byte[(byte) 0xdd];
    private static final byte[] OFF_BYTE = new byte[(byte) 0xee];
    private static final byte[] HEARTBEAT_BYTE = new byte[(byte) 0xff];

    public RobotVision() {
        initSerial();
    }

    @Override
    protected void initDefaultCommand() {
    }

    @Override
    public void periodic() {
        heartbeat();
        updateVisionCoordinates();
    }

    private void initSerial() {
        serialPort = new SerialPort(baudRate, Port.kMXP);

        // Write 'on' to Pi/Jetson
        serialPort.write(ON_BYTE, 1);
        serialPort.flush();
    }

    private void heartbeat() {
        // Write Heartbeat to Pi/Jetson
        serialPort.write(HEARTBEAT_BYTE, 1);
        serialPort.flush();
    }

    private void updateVisionCoordinates() {
        // Read from Pi/Jestson over serial
        byte[] coordinates = serialPort.read(4);
    }

}
