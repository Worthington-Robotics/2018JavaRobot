package org.usfirst.frc.team4145.robot;

import org.usfirst.frc.team4145.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.command.Command;

public class AutoSelector {
	
	
	public static Command autoSelect(String GameData, String autoSelected) {
		int UsrAuto = getUserAuto(autoSelected);
		int fieldPos = getFieldPos(GameData);
		switch(UsrAuto*10+fieldPos) {
		case 11: return new ExampleCommand();
		case 12: return new ExampleCommand();
		case 13: return new ExampleCommand();
		case 14: return new ExampleCommand();
		case 21: return new ExampleCommand();
		case 22: return new ExampleCommand();
		case 23: return new ExampleCommand();
		case 24: return new ExampleCommand();
		case 31: return new ExampleCommand();
		case 32: return new ExampleCommand();
		case 33: return new ExampleCommand();
		case 34: return new ExampleCommand();
		case 41: return new ExampleCommand();
		case 42: return new ExampleCommand();
		case 43: return new ExampleCommand();
		case 44: return new ExampleCommand();
		default: return null; // this happens if UsrAuto == 5
			
		}
		
	}
	
	private static int getUserAuto(String autoSelected) {
		switch(autoSelected) {
		case "Station 1": return 1;
		case "Station 2 Left": return 2;
		case "Station 2 Right": return 3;
		case "Station 3": return 4;
		case "DO NOTHING": // This selected case will cause the robot to do nothing
		default: //if they dont chose an auto then default to nothing as well 
			return 5;
		}
	}
	
	private static int getFieldPos(String GameData) {
		GameData.toLowerCase(); //ensure all data in known form
		char c0 = GameData.charAt(0);
		char c1 = GameData.charAt(1);
		if(c0 == 'l' && c1 == 'l') {
			return 1;
		}
		else if(c0 == 'l' && c1 == 'r') {
			return 2;
		}
		else if(c0 == 'r' && c1 == 'l') {
			return 3;
		}
		else { //essentially r r
			return 4;
		}
	}
	
}
