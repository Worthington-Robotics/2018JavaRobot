package org.usfirst.frc.team4145.robot;

public class Constants {



    /*
     * ---------------------
     * || Shared Constants ||
     * ---------------------
     */

    private static boolean isCompBot = false; //change this to use competition or non-competition constants
    public static String ROBOT_NAME = "Cube Crusher";

    public static boolean ENABLE_MP_TEST_MODE = false; //enables Teleop Motion profiling test mode code in teleop drive

    public static double DRIVETRAIN_UPDATE_RATE = 0.005;
    public static double LOGGING_UPDATE_RATE = 0.020;

    public static String DRIVE_PATH_1 = "/media/sda1";
    public static String DRIVE_PATH_2 = "/media/sda2";

    public static int LIFT_MOVE_TO_TOP = 200;

    /*
     * ---------------------
     * || Non-Split yet Constants ||
     * ---------------------
     */

    private static double DRIVETO_KP = 0.0040; //nominal 0.0040
    private static double DRIVETO_KI = 0.0000; //nominal 0.0000
    private static double DRIVETO_KD = 0.0250; //nominal 0.0250
    private static double DRIVETO_LIM = 0.6000; //nominal 0.6

    private static double TELEOP_DEADBAND = 0.1500; //nominal deadband 0.1500
    private static double TELEOP_Y_PERCENTAGE = 0.7500; //nominal decrease to y ouput percentage 0.7500
    private static double TELEOP_Y_CUT_PERCENTAGE = 0.5000; //nominal fine adjust y cut 0.5000
    private static double TELEOP_X_PERCENTAGE = 1.000; //nominal decrease to x output percentage 1.0000
    private static double TELEOP_X_CUT_PERCENTAGE = 1.0000; //nominal fine adjust x cut percentage
    private static double TELEOP_Z_PERCENTAGE = 0.5000; //nominal decrease to z output percentage

    private static double GYROLOCK_KP = 0.0330; //nominal 0.0330
    private static double GYROLOCK_KI = 0.0000; //nominal 0.0000
    private static double GYROLOCK_KD = 0.0550; //nominal 0.0550
    private static double GYROLOCK_TOL = 1.0000; //nominal 1.0000
    private static double GYROLOCK_LIM = 0.6500; //nominal 0.6500

    private static double LIFTTO_KP = 0.0200;
    private static double LIFTTO_KI = 0.0000;
    private static double LIFTTO_KD = 0.0000;
    private static double LIFTTO_TOL = 1.0000;
    private static double LIFTTO_LIM = 1.0000;

    public static double getDrivetoKp() {
        return DRIVETO_KP;
    }

    public static double getDrivetoKi(){
        return DRIVETO_KI;
    }

    public static double getDrivetoKd() {
        return DRIVETO_KD;
    }

    public static double getDrivetoLim() {
        return DRIVETO_LIM;
    }

    public static double getTeleopDeadband() {
        return TELEOP_DEADBAND;
    }

    public static double getTeleopYPercentage() {
        return TELEOP_Y_PERCENTAGE;
    }

    public static double getTeleopYCutPercentage() {
        return TELEOP_Y_CUT_PERCENTAGE;
    }

    public static double getTeleopXPercentage() {
        return TELEOP_X_PERCENTAGE;
    }

    public static double getTeleopXCutPercentage() {
        return TELEOP_X_CUT_PERCENTAGE;
    }

    public static double getTeleopZPercentage() {
        return TELEOP_Z_PERCENTAGE;
    }

    public static double getGyrolockKp() {
        return GYROLOCK_KP;
    }

    public static double getGyrolockKi(){
        return GYROLOCK_KI;
    }

    public static double getGyrolockKd() {
        return GYROLOCK_KD;
    }

    public static double getGyrolockTol() {
        return GYROLOCK_TOL;
    }

    public static double getGyrolockLim() {
        return GYROLOCK_LIM;
    }

    public static double getLifttoKp() {
        return LIFTTO_KP;
    }

    public static double getLifttoKi() {
        return LIFTTO_KI;
    }

    public static double getLifttoKd() {
        return LIFTTO_KD;
    }

    public static double getLifttoTol() {
        return LIFTTO_TOL;
    }

    public static double getLifttoLim() {
        return LIFTTO_LIM;
    }

    /*
     * ---------------------
     * || Spilt Constants ||
     * ---------------------
     */

    private static double PRACTICE_OFFSET = 0.2350; //nominal 0.2350
    private static double PRACTICE_GYRO_KP = 0.1300; //nominal 0.1300

    private static double PRACTICE_LEFT_KV = 0.0300; //nominal 0.0300
    private static double PRACTICE_LEFT_KA = 0.0300; //nominal 0.0300
    private static double PRACTICE_LEFT_KP = 1.7000; //nominal 1.7000
    private static double PRACTICE_LEFT_KD = 0.0005; //nominal 0.0005

    private static double PRACTICE_RIGHT_KV = 0.0300; //nominal 0.0300
    private static double PRACTICE_RIGHT_KA = 0.0300; //nominal 0.0300
    private static double PRACTICE_RIGHT_KP = 1.7000; //nominal 1.7000
    private static double PRACTICE_RIGHT_KD = 0.0005; //nominal 0.0005



    private static double COMPETION_OFFSET = 0.1400; //nominal 0.1400
    private static double COMPETITION_GYRO_KP = 0.3800; //nominal 0.3800

    private static double COMPETITION_LEFT_KV = 0.0360;
    private static double COMPETITION_LEFT_KA = 0.0300;
    private static double COMPETITION_LEFT_KP = 2.1000;
    private static double COMPETITION_LEFT_KD = 0.0005;

    private static double COMPETITION_RIGHT_KV = 0.0360;
    private static double COMPETITION_RIGHT_KA = 0.0300;
    private static double COMPETITION_RIGHT_KP = 2.1000;
    private static double COMPETITION_RIGHT_KD = 0.0005;

    public static boolean isCompBot() {
        return isCompBot;
    }

    public static double getOffset(){
        return isCompBot? COMPETION_OFFSET: PRACTICE_OFFSET;
    }

    public static double getGyroKp(){
        return isCompBot? COMPETITION_GYRO_KP: PRACTICE_GYRO_KP;
    }

    public static double getLeftKV(){
        return isCompBot? COMPETITION_LEFT_KV: PRACTICE_LEFT_KV;
    }

    public static double getLeftKA(){
        return isCompBot? COMPETITION_LEFT_KA: PRACTICE_LEFT_KA;
    }

    public static double getLeftKP(){
        return isCompBot? COMPETITION_LEFT_KP: PRACTICE_LEFT_KP;
    }

    public static double getLeftKD(){
        return isCompBot? COMPETITION_LEFT_KD: PRACTICE_LEFT_KD;
    }

    public static double getRightKV(){
        return isCompBot? COMPETITION_RIGHT_KV: PRACTICE_RIGHT_KV;
    }

    public static double getRightKA(){
        return isCompBot? COMPETITION_RIGHT_KA: PRACTICE_RIGHT_KA;
    }

    public static double getRightKP(){
        return isCompBot? COMPETITION_RIGHT_KP: PRACTICE_RIGHT_KP;
    }

    public static double getRightKD(){
        return isCompBot? COMPETITION_RIGHT_KD: PRACTICE_RIGHT_KD;
    }

}
