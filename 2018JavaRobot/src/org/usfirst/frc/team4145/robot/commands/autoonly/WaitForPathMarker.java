package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;

import java.util.Set;

public class WaitForPathMarker extends Command {

    private String marker;

    public WaitForPathMarker(String marker){
        this.marker = marker;
    }

    public boolean isFinished(){
        Set<String> markers = RobotMap.robotDriveV4.getPathMarkersCrossed();
        return (markers != null && markers.contains(marker));
    }

}
