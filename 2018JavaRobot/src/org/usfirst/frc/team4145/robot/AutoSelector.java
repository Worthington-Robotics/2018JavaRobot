package org.usfirst.frc.team4145.robot;

import org.usfirst.frc.team4145.robot.autocommandgroups.*;
import org.usfirst.frc.team4145.robot.shared.CommandQueueGroup;



import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Cole Tucker This enum encompasses all user selectable autonomous
 * commands.
 * <p>
 * It has two parameters, a visible name and an associated ID number
 * from 1 to n.
 */

enum UserSelection {

    Auto1("Position 1 Switch", 1), Auto2("Position 1 Scale", 2), Auto3("Position 1 Exchange", 3), Auto4("Position 2 Switch", 4),
    Auto5("Position 2 Scale", 5), Auto6("Position 2 Exchange", 6), Auto7("Position 3 Switch", 7), Auto8("Position 3 Scale", 8),
    Auto9("Position 3 Exchange", 9), Auto10("Auto 10", 10), Auto11("Auto 11", 11), Auto12("Auto 12", 12), Auto13("Do Nothing", 13);

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
        //System.out.println("Auto choice:" + (usrAuto * 10 + fieldPos));
        switch (usrAuto * 10 + fieldPos) {
            case 11:
                return new SaahibSwitch(1).getQueuedStates();
            case 12:
                return new SaahibSwitch(1).getQueuedStates();
            case 13:
                return new SaahibSwitch(1).getQueuedStates();
            case 14:
                return new SaahibSwitch(1).getQueuedStates();
            case 21:
                return new Postion1Scale(0).getQueuedStates();
            case 22:
                return new Postion1Scale(1).getQueuedStates();
            case 23:
                return new Postion1Scale(0).getQueuedStates();
            case 24:
                return new Postion1Scale(1).getQueuedStates();
            case 31:
                return new PhillipExchange(1).getQueuedStates();
            case 32:
                return new PhillipExchange(1).getQueuedStates();
            case 33:
                return new PhillipExchange(1).getQueuedStates();
            case 34:
                return new PhillipExchange(1).getQueuedStates();
            case 41:
                return new FongSwitch(0).getQueuedStates(); //Switch Right
            case 42:
                return new FongSwitch(0).getQueuedStates();
            case 43:
                return new FongSwitch(1).getQueuedStates(); //Switch Left
            case 44:
                return new FongSwitch(1).getQueuedStates();
            case 51:
                return new VedScale(0).getQueuedStates();
            case 52:
                return new VedScale(0).getQueuedStates();
            case 53:
                return new VedScale(1).getQueuedStates();
            case 54:
                return new VedScale(1).getQueuedStates();
            case 61:
                return new PhillipExchange(2).getQueuedStates();
            case 62:
                return new PhillipExchange(2).getQueuedStates();
            case 63:
                return new PhillipExchange(2).getQueuedStates();
            case 64:
                return new PhillipExchange(2).getQueuedStates();
            case 71:
                return new MeinekeSwitch(0).getQueuedStates();
            case 72:
                return new MeinekeSwitch(0).getQueuedStates();
            case 73:
                return new MeinekeSwitch(1).getQueuedStates(); 
            case 74:
                return new MeinekeSwitch(1).getQueuedStates();
            case 81:
                return new AdamScale(0).getQueuedStates();
            case 82:
                return new AdamScale(1).getQueuedStates();
            case 83:
                return new AdamScale(0).getQueuedStates();
            case 84:
                return new AdamScale(1).getQueuedStates();
            case 91:
                return new PhillipExchange(3).getQueuedStates();
            case 92:
                return new PhillipExchange(3).getQueuedStates();
            case 93:
                return new PhillipExchange(3).getQueuedStates();
            case 94:
                return new PhillipExchange(3).getQueuedStates();
            case 101:
                return null;
            case 102:
                return null;
            case 103:
                return null;
            case 104:
                return null;
            case 111:
                return null;
            case 112:
                return null;
            case 113:
                return null;
            case 114:
                return null;
            case 121:
                return null;
            case 122:
                return null;
            case 123:
                return null;
            case 124:
                return null;
            default:
                return null; // this happens if UsrAuto == 5
         	
        }
    }

}
