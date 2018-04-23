package org.usfirst.frc.team4145.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.autocommandgroups.*;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;


/**
 * @author Cole Tucker This enum encompasses all user selectable autonomous
 * commands.
 * <p>
 * It has two parameters, a visible name and an associated ID number
 * from 1 to n.
 */

enum UserSelection {


    Auto1("Position 1 Scale Wait", 1),
    Auto2("Position 1 Scale Deliver", 2),
    Auto3("Position 1 Scale Backside Switch", 3),
    Auto4("Position 2 Switch", 4),
    Auto5("Position 3 Scale Wait", 5),
    Auto6("Position 3 Scale Deliver",6),
    Auto7("Position 3 Scale Backside Switch", 7),
    Auto8("Cross The Line",8),
    Auto9("Cross The Line Long", 9),
    Auto10("Robot Test", 10),
    Auto11("Auto Test - 10ft by 10ft", 11),
    Auto20("Do Nothing", 20);

    private String name;
    private int num;

    UserSelection(String name, int num) {
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
     * @param name checked against all possible enum name parameters
     * @return The internally used enum for auto calculations
     */
    private static UserSelection getSelFromStr(String name) {
        for (UserSelection sel : UserSelection.values()) {
            if (sel.toString().equalsIgnoreCase(name)) {
                return sel;
            }
        }
        // return the last possible enum by default
        return UserSelection.values()[UserSelection.values().length-1];
    }

    private static int getFieldPos(String GameData) {
        GameData = GameData.toLowerCase(); // ensure all data in known form
        //Switch first then Scale
        char c0 = GameData.charAt(0);
        char c1 = GameData.charAt(1);
        //System.out.println("Game data " + c0 + " " + c1);
        if (c0 == 'l' && c1 == 'l') {
            return 1; //LL
        } else if (c0 == 'l' && c1 == 'r') {
            return 2; //LR
        } else if (c0 == 'r' && c1 == 'l') {
            return 3; //RL
        } else {
            return 4; //RR
        }
    }

    /**
     * This method determines the Auto mode based on the fed game data and the
     * dashboard data.
     *
     * @param GameData  - a string containing the field positions of the team objectives.
     * @param selection - a string with the name of the selected autonomous.
     * @return the proper auto command to run. It should include all movements in
     * one command
     */
    public static CommandGroupV2 autoSelect(String GameData, String selection) {
        int usrAuto = getSelFromStr(selection).getNum();
        int fieldPos = getFieldPos(GameData);
        SmartDashboard.putNumber("Final Auto Choice", (usrAuto * 10 + fieldPos));
        switch (usrAuto * 10 + fieldPos) {

            case 11: return new Position1Scale(0); //scale left
            case 12: return new Position1Scale(1); //scale right - no deliver
            case 13: return new Position1Scale(0); //scale left
            case 14: return new Position1Scale(1); //scale right - no deliver

            case 21: return new Position1Scale(0); //scale left
            case 22: return new Position1Scale(2); //scale right - deliver
            case 23: return new Position1Scale(0); //scale left
            case 24: return new Position1Scale(2); //scale right - deliver

            case 31: return new Position1Scale(0); //scale left
            case 32: return new Position1Scale(3); //scale right - switch
            case 33: return new Position1Scale(0); //scale left
            case 34: return new Position1Scale(1); //scale right - no deliver

            case 41: return new Position2Switch(0); //Switch Right
            case 42: return new Position2Switch(0); //Switch Right
            case 43: return new Position2Switch(1); //Switch Left
            case 44: return new Position2Switch(1); //Switch Left

            case 51: return new Position3Scale(1); //scale left  - no deliver
            case 52: return new Position3Scale(0); //scale right
            case 53: return new Position3Scale(1); //scale left  - no deliver
            case 54: return new Position3Scale(0); //scale right

            case 61: return new Position3Scale(2); //scale left  - deliver
            case 62: return new Position3Scale(0); //scale right
            case 63: return new Position3Scale(2); //scale left  - deliver
            case 64: return new Position3Scale(0); //scale right

            case 71: return new Position3Scale(1); //scale left  - no deliver
            case 72: return new Position3Scale(0); //scale right
            case 73: return new Position3Scale(3); //scale left  - switch
            case 74: return new Position3Scale(0); //scale right

            case 81:
            case 82:
            case 83:
            case 84: return new CrossTheLine();

            case 91:
            case 92:
            case 93:
            case 94: return new CrossLineLong();

            case 101:
            case 102:
            case 103:
            case 104: return new AutoTestProct();

            case 111:
            case 112:
            case 113:
            case 114: return new test();


            default: return null;
         	
        }
    }

}
