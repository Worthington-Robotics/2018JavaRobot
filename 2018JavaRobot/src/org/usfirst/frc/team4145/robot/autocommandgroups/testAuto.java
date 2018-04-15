package org.usfirst.frc.team4145.robot.autocommandgroups;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4145.robot.shared.AutoStateMachine.FollowPath;

import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class testAuto extends CommandGroup {

    public testAuto(){
        List<Path.Waypoint> first_path = new ArrayList<>();
        first_path.add(new Path.Waypoint(new Translation2d(0, 0), 50.0));
        first_path.add(new Path.Waypoint(new Translation2d(18, 0),  50.0));
        first_path.add(new Path.Waypoint(new Translation2d(60,-60),50.0));
        first_path.add(new Path.Waypoint(new Translation2d(100,-60),50.0, "end"));
        first_path.add(new Path.Waypoint(new Translation2d(102,-60),50.0));

        List<Path.Waypoint> second_path = new ArrayList<>();
        second_path.add(new Path.Waypoint(new Translation2d(102,-60),40.0));
        second_path.add(new Path.Waypoint(new Translation2d(50,-60),40.0));

        List<Path.Waypoint> third_path = new ArrayList<>();
        third_path.add(new Path.Waypoint(new Translation2d(50,-60),60.0));
        third_path.add(new Path.Waypoint(new Translation2d(72,-24),60.0));

        List<Path.Waypoint> fourth_path = new ArrayList<>();
        fourth_path.add(new Path.Waypoint(new Translation2d(72,-24),60.0));
        fourth_path.add(new Path.Waypoint(new Translation2d(50,-60),60.0));

        List<Path.Waypoint> fifth_path = new ArrayList<>();
        fifth_path.add(new Path.Waypoint(new Translation2d(50,-60),50.0));
        fifth_path.add(new Path.Waypoint(new Translation2d(100,-60),50.0, "end 2"));
        fifth_path.add(new Path.Waypoint(new Translation2d(102,-60),50.0));
        
        addSequential(new FollowPath(new Path(first_path), false), 20000);
        addSequential(new WaitForPathMarker("end"), 10000);
        //addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
        addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 500);

        addParallel(new LiftToPosition(-850), 3000);
        addSequential(new FollowPath(new Path(seccond), true), 20000);
        addSequential(new DriveStateWait(), 3000);
        addSequential(new GyroToAngle(48), 500);
        /*
        addDrive(third_path, false);
        addSequential(new CubeMovement(CubeMovement.CubeState.Pickup), 2500);

        addDrive(fourth_path, true,500);
        addSequential(new LiftToPosition(700), 2000);

        addDrive(fifth_path, false);
        addSequential(new LiftToPosition(150), 1000);
        addSequential(new WaitForPathMarker("end 2"), 10000);
        addSequential(new ContingentWait(ContingentWait.Target.Switch), 20000);
        addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1000);*/
    }

}
