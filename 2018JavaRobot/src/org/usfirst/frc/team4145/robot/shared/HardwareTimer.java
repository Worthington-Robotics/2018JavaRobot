package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;

public class HardwareTimer {

    public static void hardwareSleep(long SLEEP_LENGTH_MS){
        long FPGA_TIMESTAMP_AT_ENTRY = RobotController.getFPGATime();
        while( RobotController.getFPGATime() < (FPGA_TIMESTAMP_AT_ENTRY + (SLEEP_LENGTH_MS * 1000))){
            //maybe some other delaying task?

        }
    }

}
