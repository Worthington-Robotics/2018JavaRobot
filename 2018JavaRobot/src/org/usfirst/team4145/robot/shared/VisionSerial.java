package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class VisionSerial {

	private SerialPort serialPort;
	private byte[] coordinates;
    private static final byte[] ON_BYTE = new byte[(byte) 0xdd];
    private static final byte[] OFF_BYTE = new byte[(byte) 0xee];
    private static final byte[] HEARTBEAT_BYTE = new byte[(byte) 0xff];

	public VisionSerial(int baudRate) {
		serialPort = new SerialPort(baudRate, Port.kMXP);
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
        coordinates = serialPort.read(4);
    }
    
    public void off() {
    	// Turns off Pi/Jetson over serial
    	serialPort.write(OFF_BYTE, 1);
    }
    
    public double getCoords() {
    	return 0.0;
    }
    
    private double decode(byte[] data) {
    	double out = 0.0;
    	for(int a: data) {
    		
    	}
    	return out;

    }


}