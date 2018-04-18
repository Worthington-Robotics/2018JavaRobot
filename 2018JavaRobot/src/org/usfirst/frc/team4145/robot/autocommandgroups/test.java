package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.FollowPath;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;

import java.util.ArrayList;
import java.util.List;

public class test extends CommandGroupV2 {

    public test() {
        List<Path.Waypoint> first_path = new ArrayList<>();
        first_path.add(new Path.Waypoint(new Translation2d(0, 0), 0.0));
        first_path.add(new Path.Waypoint(new Translation2d(60, 0), 40.0));
        first_path.add(new Path.Waypoint(new Translation2d(120, 60), 40.0));
        first_path.add(new Path.Waypoint(new Translation2d(120, 120), 40.0));

        addParallel(new FollowPath(new Path(first_path), false), 20.000);
    }

}
