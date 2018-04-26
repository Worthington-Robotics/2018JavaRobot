package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.FollowPath;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;

import java.util.ArrayList;
import java.util.List;

public class CrossLineLong extends CommandGroupV2 {
    public CrossLineLong(){

        List<Path.Waypoint> first_path = new ArrayList<>();
        first_path.add(new Path.Waypoint(new Translation2d(0, 0), 40.0));
        first_path.add(new Path.Waypoint(new Translation2d(156, 0),  40.0));

        addSequential(new FollowPath(new Path(first_path), false));
    }
}
