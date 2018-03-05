package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.Timer;

public class HardwareTimer {

    public static void hardwareSleep(long SLEEP_LENGTH_MS){
        double FPGA_TIMESTAMP_AT_ENTRY = Timer.getFPGATimestamp() * 1000;
        while(Timer.getFPGATimestamp() * 1000 < (FPGA_TIMESTAMP_AT_ENTRY + SLEEP_LENGTH_MS)){
            //maybe some other delaying task?
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e){

            }
        }
    }

}
