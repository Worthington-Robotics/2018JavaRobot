package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.Robot;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoggingSystem {

    private File base;
    private PrintWriter printWriter;
    private List<String> smartDashKeys;
    private String toWrite;
    private Notifier loggerThread;
    private boolean initSuccess;
    private Runnable runnable = () -> logLine();

    public LoggingSystem(){
        initSuccess = false;
        smartDashKeys = new ArrayList<>();
        loggerThread = new Notifier(runnable);
        try {
            base = getMount();
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(base)));
            initSuccess = true;
        } catch (Exception e) {
            DriverStation.reportError("Failed to initialize log on file!", false);
            //e.printStackTrace();
        }
    }

    public void addWatchKey(String key){
        smartDashKeys.add(key);
    }

    public void enablePrint(boolean enable){
        if(initSuccess) {
            if (enable) {
                loggerThread.startPeriodic(Constants.LOGGING_UPDATE_RATE);
            } else {
                loggerThread.stop();
            }
        }
        else{
            DriverStation.reportWarning("logger called to init on Null file stream", false);
        }
    }

    private void logLine(){
        if(printWriter != null) {
            toWrite = "" + Timer.getFPGATimestamp() + "\t";
            for (String key : smartDashKeys) {
                toWrite += "" + SmartDashboard.getNumber(key, 0.0) + "\t";
            }
            toWrite += "\r\n";
            printWriter.write(toWrite);
            printWriter.flush();
        }
    }

    public static String WriteBuildInfoToDashboard() {
        String COMP_MSG = "THIS IS THE COMPETITION SOFTWARE CONFIG! CHECK IF ROBOT MATCHES!\n";

        String PRAC_MSG = "THIS IS THE PRACTICE SOFTWARE CONFIG! CHECK IF ROBOT MATCHES!\n";
        String buildMsg = Constants.isCompBot()? COMP_MSG: PRAC_MSG ;
        DriverStation.reportWarning(buildMsg, false);
        try {
            //get the path of the currently executing jar file
            String currentJarFilePath = Robot.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            Path filePath = Paths.get(currentJarFilePath);

            //get file system details from current file
            BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
            Date utcFileDate = new Date(attr.lastModifiedTime().toMillis());

            // convert from UTC to local time zone
            SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            outputFormatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            String newDateString = outputFormatter.format(utcFileDate);

            // write the build date & time to the operator's console log window
            buildMsg = "== Robot Name == " + Constants.ROBOT_NAME + "| Build Date and Time: " + newDateString + "|";
            DriverStation.reportWarning(buildMsg, false);
        } catch (URISyntaxException e) {
            DriverStation.reportWarning("Error determining filename of current JAR file", true);
            //e.printStackTrace();
        } catch (IOException e) {
            DriverStation.reportWarning("General Error trying to determine current JAR file", true);
            //e.printStackTrace();
        }

        return buildMsg;
    }

    private File getMount() {
        File mountPoint;
        // find the mount point
        File testPoint = new File(Constants.DRIVE_PATH_1 + "/logging");
        if (testPoint.isDirectory()) { //drive exists on sda
            mountPoint = testPoint;
        } else {
            testPoint = new File(Constants.DRIVE_PATH_2 + "/logging");
            if (testPoint.isDirectory()) {//drive exists on sdb
                mountPoint = testPoint;
            } else {
                mountPoint = null;
            }
        }
        if (mountPoint != null){
            SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
            outputFormatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            String newDateString = outputFormatter.format(new Date());
            // build the new filename
            String fileName = newDateString + "_LOG.tsv";
            // build the full file path name
            return new File(mountPoint.getAbsolutePath() + File.separator + fileName);
        }
        return null;
    }

}
