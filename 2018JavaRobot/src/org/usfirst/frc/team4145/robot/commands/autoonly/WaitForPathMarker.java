package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.RobotMap;

import java.util.Set;

public class WaitForPathMarker extends Command {

    private String marker;

    public WaitForPathMarker(String marker){
        this.marker = marker;
    }

    public boolean isFinished(){
        Set<String> markers = RobotMap.robotDriveV4.getPathMarkersCrossed();
        //System.out.println("path markers crossed: " + markers.toString());
        SmartDashboard.putString("Marker completion","path to " + marker + " is " + ((markers != null && markers.contains(marker))? "finished": "not finished"));
        return ((markers != null) && markers.contains(marker));
    }

}
