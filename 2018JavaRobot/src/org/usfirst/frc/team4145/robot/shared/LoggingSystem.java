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
    private Runnable runnable = () -> logLine();

    public LoggingSystem(){
        smartDashKeys = new ArrayList<>();
        loggerThread = new Notifier(runnable);
        try {
            base = getMount();
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(base)));
        } catch (IOException e) {
            DriverStation.reportError("Failed to initialize log on file!", false);
            //e.printStackTrace();
        }
    }

    public void addWatchKey(String key){
        smartDashKeys.add(key);
    }

    public void enablePrint(boolean enable){
        if(enable){
            loggerThread.startPeriodic(Constants.LOGGING_UPDATE_RATE);
        }
        else {
            loggerThread.stop();
        }
    }

    private void logLine(){
        if(printWriter != null)
        toWrite = "" + Timer.getFPGATimestamp() + "\t";
        for(String key : smartDashKeys){
            toWrite += "" + SmartDashboard.getNumber(key, 0.0) + "\t";
        }
        toWrite += "\r\n";
        printWriter.write(toWrite);
        printWriter.flush();
    }

    public static String WriteBuildInfoToDashboard(String robotName) {
        String buildMsg = "?";
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
            buildMsg = "== Robot Name == " + robotName + "| Build Date and Time: " + newDateString + "|";
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

    private File getMount(){
        File mountPoint = null;
        // find the mount point
        mountPoint = new File()
        mountPoint = new File(mountPoint, "/logging");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        outputFormatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
        String newDateString = outputFormatter.format(new Date());

        // build the new filename
        String fileName = newDateString + "_LOG.tsv";
        // build the full file path name
        return new File(mountPoint.getAbsolutePath() + File.separator + fileName);
    }

}
