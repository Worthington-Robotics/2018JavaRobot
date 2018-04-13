package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class testAuto extends QueueGroup {

    public testAuto(int variant){
        List<Path.Waypoint> first_path = new ArrayList<>();
        first_path.add(new Path.Waypoint(new Translation2d(0, 0), 40.0));
        first_path.add(new Path.Waypoint(new Translation2d(120, 0),  40.0));
        first_path.add(new Path.Waypoint(new Translation2d(180,60),40.0));
        addDrive(first_path, false);
    }

}
