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
		case 51: return new ExampleCommand();
		case 52: return new ExampleCommand();
		case 53: return new ExampleCommand();
		case 54: return new ExampleCommand();
		case 61: return new ExampleCommand();
		case 62: return new ExampleCommand();
		case 63: return new ExampleCommand();
		case 64: return new ExampleCommand();
		case 71: return new ExampleCommand();
		case 72: return new ExampleCommand();
		case 73: return new ExampleCommand();
		case 74: return new ExampleCommand();
		case 81: return new ExampleCommand();
		case 82: return new ExampleCommand();
		case 83: return new ExampleCommand();
		case 84: return new ExampleCommand();
		case 91: return new ExampleCommand();
		case 92: return new ExampleCommand();
		case 93: return new ExampleCommand();
		case 94: return new ExampleCommand();
		case 101: return new ExampleCommand();
		case 102: return new ExampleCommand();
		case 103: return new ExampleCommand();
		case 104: return new ExampleCommand();
		case 111: return new ExampleCommand();
		case 112: return new ExampleCommand();
		case 113: return new ExampleCommand();
		case 114: return new ExampleCommand();
		case 121: return new ExampleCommand();
		case 122: return new ExampleCommand();
		case 123: return new ExampleCommand();
		case 124: return new ExampleCommand();
		default: return null; // this happens if UsrAuto == 5
			
		}
		
	}
	
	private static int getUserAuto(String autoSelected) {
		switch(autoSelected) {
		case "Auto1": return 1;
		case "Auto2": return 2;
		case "Auto3": return 3;
		case "Auto4": return 4;
		case "Auto5": return 5;
		case "Auto6": return 6;
		case "Auto7": return 7;
		case "Auto8": return 8;
		case "Auto9": return 9;
		case "Auto10": return 10;
		case "Auto11": return 11;
		case "Auto12": return 12;
		case "DO NOTHING": // This selected case will cause the robot to do nothing
		default: //if they dont chose an auto then default to nothing as well 
			return 13;
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
