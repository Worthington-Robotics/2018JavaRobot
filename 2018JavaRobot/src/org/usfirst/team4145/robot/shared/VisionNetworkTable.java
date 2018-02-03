package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("deprecation")
public class VisionNetworkTable {

	private double centerCoordinate = 0.0;
	
	private NetworkTable table;
	
	public VisionNetworkTable() {
		table = NetworkTable.getTable("vision");
	}

	public void updateVisionCoordinates() {
		centerCoordinate = table.getNumber("centerX", 0.0);
		SmartDashboard.putNumber("center", centerCoordinate);
	}

}