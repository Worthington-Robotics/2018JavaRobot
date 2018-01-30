package org.usfirst.team4145.robot.shared;

import java.io.UnsupportedEncodingException;

import org.usfirst.frc.team4145.robot.RobotMap;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionSerial {

	private SerialPort serialPort;
	private byte[] coordinates;
	private static final byte[] ON_BYTE = new byte[1];
	private static final byte[] OFF_BYTE = new byte[1];
	private static final byte[] HEARTBEAT_BYTE = new byte[1];
	private static final double PIXEL_TARGET = 160.0;
	private static final double PIXEL_PER_DEGREE = 6.62;

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
		coordinates = serialPort.read(6);
		SmartDashboard.putRaw("coordinates", coordinates);
		System.out.println("SERIAL OUTPUT");
		String decoded = new String(coordinates);
		System.out.println(decoded);
		SmartDashboard.putString("decoded", decoded);
	
	}

	public void off() {
		// Turns off Pi/Jetson over serial
		serialPort.write(OFF_BYTE, 1);
	}

	public double getCoords() {
		return decode(coordinates);
	}

	private double decode(byte[] data) {
		double out = 0.0;
		if (data.length >= 6) {
			out += ((double) data[0] - 48) * 100;
			out += ((double) data[1] - 48) * 10;
			out += ((double) data[2] - 48);
			// .
			out += ((double) data[4] - 48) * 0.1;
			out += ((double) data[5] - 48) * 0.01;
		}
		return out;

	}

	public double getTargetAngle(double currentCenter) {
		double pixelOffset = currentCenter - PIXEL_TARGET;
		double angleOffset = pixelOffset / PIXEL_PER_DEGREE;
		double gyroValue = (RobotMap.ahrs.getYaw() + angleOffset + 180) % 360;
		return gyroValue;

	}

}