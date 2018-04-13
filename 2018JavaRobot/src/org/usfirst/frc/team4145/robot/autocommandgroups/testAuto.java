package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.ContingentWait;
import org.usfirst.frc.team4145.robot.commands.autoonly.CubeMovement;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.WaitForPathMarker;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.QueueGroup;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class testAuto extends QueueGroup {

    public testAuto(int variant){
        List<Path.Waypoint> first_path = new ArrayList<>();
        first_path.add(new Path.Waypoint(new Translation2d(0, 0), 50.0));
        first_path.add(new Path.Waypoint(new Translation2d(18, 0),  50.0));
        first_path.add(new Path.Waypoint(new Translation2d(60,-60),50.0));
        first_path.add(new Path.Waypoint(new Translation2d(100,-60),50.0, "end"));
        first_path.add(new Path.Waypoint(new Translation2d(102,-60),50.0));

        List<Path.Waypoint> second_path = new ArrayList<>();
        second_path.add(new Path.Waypoint(new Translation2d(102,-60),40.0));
        second_path.add(new Path.Waypoint(new Translation2d(52,-60),40.0, "end 2"));
        second_path.add(new Path.Waypoint(new Translation2d(50,-60),40.0));

        List<Path.Waypoint> third_path = new ArrayList<>();
        third_path.add(new Path.Waypoint(new Translation2d(50,-60),40.0));
        third_path.add(new Path.Waypoint(new Translation2d(85,-9),40.0, "end 3"));
        third_path.add(new Path.Waypoint(new Translation2d(87,-7),40.0));

        List<Path.Waypoint> fourth_path = new ArrayList<>();
        fourth_path.add(new Path.Waypoint(new Translation2d(87,-7),40.0));
        fourth_path.add(new Path.Waypoint(new Translation2d(62,-58),40.0, "end 4"));
        fourth_path.add(new Path.Waypoint(new Translation2d(60,-60),40.0));

        List<Path.Waypoint> fifth_path = new ArrayList<>();
        fifth_path.add(new Path.Waypoint(new Translation2d(60,-60),50.0));
        fifth_path.add(new Path.Waypoint(new Translation2d(100,-60),50.0, "end 5"));
        fifth_path.add(new Path.Waypoint(new Translation2d(102,-60),50.0));

        addDrive(first_path, false);
        addSequential(new WaitForPathMarker("end"), 10000);
        addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
        addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1000);
        addDrive(second_path, true);
        addSequential(new WaitForPathMarker("end 2"), 10000);
        addDrive(third_path, false);
        addSequential(new WaitForPathMarker("end 3"), 10000);
        addDrive(fourth_path, true);
        addSequential(new WaitForPathMarker("end 4"), 10000);
        addDrive(fifth_path, false);
        addSequential(new WaitForPathMarker("end 5"), 10000);
        addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
        addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1000);
    }

}
