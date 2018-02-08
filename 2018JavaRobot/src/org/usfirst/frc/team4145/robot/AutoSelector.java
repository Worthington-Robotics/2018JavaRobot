package org.usfirst.frc.team4145.robot;

import org.usfirst.frc.team4145.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Cole Tucker This enum encompasses all user selectable autonomous
 *         commands.
 *         <p>
 *         It has two parameters, a visible name and an associated ID number
 *         from 1 to n.
 *
 */

enum UserSelection {

	Auto1("Auto 1", 1), Auto2("Auto 2", 2), Auto3("Auto 3", 3), Auto4("Auto 4", 4), Auto5("Auto 5", 5), Auto6("Auto 6",
			6), Auto7("Auto 7", 7), Auto8("Auto 8", 8), Auto9("Auto 9",9), Auto10("Auto 10", 10), Auto11("Auto 11", 11),
	Auto12("Auto 12", 12), Auto13("Do Nothing", 13);

	private String name;
	private int num;

	private UserSelection(String name, int num) {
		this.name = name;
		this.num = num;
	}

	@Override
	public String toString() {
		return name;
	}

	public int getNum() {
		return num;
	}

}

public class AutoSelector {

	/**
	 * Method to get an array of names for all selections
	 *
	 * @return string array of enum names
	 */
	public static String[] buildArray() {
		String[] out = new String[UserSelection.values().length];
		for (int i = 0; i < UserSelection.values().length; i++) {
			out[i] = UserSelection.values()[i].toString();
		}
		return out;
	}

	/**
	 * Gets the enum object that matches the fed string.
	 *
	 * @param name
	 *            checked against all possible enum name parameters
	 * @return The internally used enum for auto calculations
	 */
	public static UserSelection getSelFromStr(String name) {
		for (UserSelection sel : UserSelection.values()) {
			if (sel.toString().equalsIgnoreCase(name)) {
				return sel;
			}
		}
		// return the last possible enum by default
		return UserSelection.values()[UserSelection.values().length];
	}

	private static int getFieldPos(String GameData) {
		GameData.toLowerCase(); // ensure all data in known form
		char c0 = GameData.charAt(0);
		char c1 = GameData.charAt(1);
		if (c0 == 'l' && c1 == 'l') {
			return 1;
		} else if (c0 == 'l' && c1 == 'r') {
			return 2;
		} else if (c0 == 'r' && c1 == 'l') {
			return 3;
		} else { // essentially r r
			return 4;
		}
	}

	/**
	 * This method determines the Auto mode based on the fed game data and the
	 * dashboard data.
	 *
	 * @param GameData
	 *            - a string containing the field positions of the team objectives.
	 * @param selection
	 *            - a string with the name of the selected autonomous.
	 * @return the proper auto command to run. It should include all movements in
	 *         one command
	 */
	public static Command autoSelect(String GameData, String selection) {
		int UsrAuto = getSelFromStr(selection).getNum();
		int fieldPos = getFieldPos(GameData);
		switch (UsrAuto * 10 + fieldPos) {
			case 11:
				return new ExampleCommand();
			case 12:
				return new ExampleCommand();
			case 13:
				return new ExampleCommand();
			case 14:
				return new ExampleCommand();
			case 21:
				return new ExampleCommand();
			case 22:
				return new ExampleCommand();
			case 23:
				return new ExampleCommand();
			case 24:
				return new ExampleCommand();
			case 31:
				return new ExampleCommand();
			case 32:
				return new ExampleCommand();
			case 33:
				return new ExampleCommand();
			case 34:
				return new ExampleCommand();
			case 41:
				return new ExampleCommand();
			case 42:
				return new ExampleCommand();
			case 43:
				return new ExampleCommand();
			case 44:
				return new ExampleCommand();
			case 51:
				return new ExampleCommand();
			case 52:
				return new ExampleCommand();
			case 53:
				return new ExampleCommand();
			case 54:
				return new ExampleCommand();
			case 61:
				return new ExampleCommand();
			case 62:
				return new ExampleCommand();
			case 63:
				return new ExampleCommand();
			case 64:
				return new ExampleCommand();
			case 71:
				return new ExampleCommand();
			case 72:
				return new ExampleCommand();
			case 73:
				return new ExampleCommand();
			case 74:
				return new ExampleCommand();
			case 81:
				return new ExampleCommand();
			case 82:
				return new ExampleCommand();
			case 83:
				return new ExampleCommand();
			case 84:
				return new ExampleCommand();
			case 91:
				return new ExampleCommand();
			case 92:
				return new ExampleCommand();
			case 93:
				return new ExampleCommand();
			case 94:
				return new ExampleCommand();
			case 101:
				return new ExampleCommand();
			case 102:
				return new ExampleCommand();
			case 103:
				return new ExampleCommand();
			case 104:
				return new ExampleCommand();
			case 111:
				return new ExampleCommand();
			case 112:
				return new ExampleCommand();
			case 113:
				return new ExampleCommand();
			case 114:
				return new ExampleCommand();
			case 121:
				return new ExampleCommand();
			case 122:
				return new ExampleCommand();
			case 123:
				return new ExampleCommand();
			case 124:
				return new ExampleCommand();
			default:
				return null; // this happens if UsrAuto == 5

		}

	}

}
