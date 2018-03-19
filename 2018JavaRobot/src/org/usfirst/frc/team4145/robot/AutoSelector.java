package org.usfirst.frc.team4145.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.autocommandgroups.*;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.CommandQueueGroup;



import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Cole Tucker This enum encompasses all user selectable autonomous
 * commands.
 * <p>
 * It has two parameters, a visible name and an associated ID number
 * from 1 to n.
 */

enum UserSelection {

    //Auto1("Position 1 Switch", 1),
    Auto2("Position 1 Scale - Profiled", 2),
    //Auto3("Position 1 Exchange", 3),
    Auto4("Position 2 Switch - Profiled", 4),
    //Auto5("Position 2 Scale", 5),
    //Auto6("Position 2 Exchange", 6),
    //Auto7("Position 3 Switch", 7),
    Auto8("Position 3 Scale", 8),
    //Auto9("Position 3 Exchange", 9),
    Auto10("Cross The Line - Profiled", 10),
    Auto11("Cross The Line Long", 11),

    Auto12("Old Position 2 Switch", 12),
    Auto13("Old Cross The Line", 13),
    Auto14("Old Position 1 Scale", 14),
    Auto15("Robot Test", 15),
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
    public static LinkedBlockingQueue<CommandQueueGroup> autoSelect(String GameData, String selection) {
        int usrAuto = getSelFromStr(selection).getNum();
        int fieldPos = getFieldPos(GameData);
        SmartDashboard.putNumber("Final Auto Choice", (usrAuto * 10 + fieldPos));
        switch (usrAuto * 10 + fieldPos) {
            
            case 11: return null; //new Position1Switch(1).getQueuedStates();
            case 12: return null; //new Position1Switch(1).getQueuedStates();
            case 13: return null; //new Position1Switch(1).getQueuedStates();
            case 14: return null; //new Position1Switch(1).getQueuedStates();

            case 21: return new Position1Scale(0).getQueuedStates();
            case 22: return new Position1Scale(1).getQueuedStates();
            case 23: return new Position1Scale(0).getQueuedStates();
            case 24: return new Position1Scale(1).getQueuedStates();

            case 31:
            case 32:
            case 33:
            case 34: return null; //new ExchangeAll(1).getQueuedStates();

            case 41: return new Position2Switch(0).getQueuedStates(); //Switch Right
            case 42: return new Position2Switch(0).getQueuedStates();
            case 43: return new Position2Switch(1).getQueuedStates(); //Switch Left
            case 44: return new Position2Switch(1).getQueuedStates();

            case 51: return null; //new Position2Scale(1).getQueuedStates();
            case 52: return null; //new Position2Scale(0).getQueuedStates();
            case 53: return null; //new Position2Scale(1).getQueuedStates();
            case 54: return null; //new Position2Scale(0).getQueuedStates();

            case 61:
            case 62:
            case 63:
            case 64: return null; //new ExchangeAll(2).getQueuedStates();

            case 71: return null; //new Position3Switch(0).getQueuedStates();
            case 72: return null; //new Position3Switch(0).getQueuedStates();
            case 73: return null; //new Position3Switch(1).getQueuedStates();
            case 74: return null; //new Position3Switch(1).getQueuedStates();

            case 81: return new Position3Scale(1).getQueuedStates();
            case 82: return new Position3Scale(0).getQueuedStates();
            case 83: return new Position3Scale(1).getQueuedStates();
            case 84: return new Position3Scale(0).getQueuedStates();

            case 91:
            case 92:
            case 93:
            case 94: return null; //new ExchangeAll(3).getQueuedStates();

            case 101:
            case 102:
            case 103:
            case 104: return new CrossTheLine().getQueuedStates();

            case 111:
            case 112:
            case 113:
            case 114: return new CrossLineLong().getQueuedStates();

            case 121: return new Position2SwitchOld(0).getQueuedStates(); //right
            case 122: return new Position2SwitchOld(0).getQueuedStates();
            case 123: return new Position2SwitchOld(1).getQueuedStates(); //left
            case 124: return new Position2SwitchOld(1).getQueuedStates();

            case 131:
            case 132:
            case 133:
            case 134: return new CrossTheLineOld().getQueuedStates();

            case 141: return new Position1ScaleOld(0).getQueuedStates();
            case 142: return new Position1ScaleOld(1).getQueuedStates();
            case 143: return new Position1ScaleOld(0).getQueuedStates();
            case 144: return new Position1ScaleOld(1).getQueuedStates();
            
            case 151:
            case 152:
            case 153:
            case 154: return new AutoTestProct().getQueuedStates();

            default: return null;
         	
        }
    }

}
