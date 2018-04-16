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


    Auto1("Position 1 Scale", 1),
    Auto2("Position 2 Switch", 2),
    Auto3("Position 3 Scale", 3),
    Auto4("Cross The Line", 4),
    Auto5("Cross The Line Long", 5),
    Auto6("Robot Test", 6),
    Auto20("Do Nothing", 20)
    ;

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
            case 12: return new Position1Scale(1); //scale right
            case 13: return new Position1Scale(0);
            case 14: return new Position1Scale(1);

            case 21: return new Position2Switch(0); //Switch Right
            case 22: return new Position2Switch(0);
            case 23: return new Position2Switch(1); //Switch Left
            case 24: return new Position2Switch(1);

            case 31: return new Position3Scale(1); //scale left
            case 32: return new Position3Scale(0); //scale right
            case 33: return new Position3Scale(1);
            case 34: return new Position3Scale(0);

            case 41:
            case 42:
            case 43:
            case 44: return new CrossTheLine();

            case 51:
            case 52:
            case 53:
            case 54: return new CrossLineLong();

            case 61:
            case 62:
            case 63:
            case 64: return new AutoTestProct();

            default: return null;
         	
        }
    }

}
