package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;

import java.util.ArrayList;
import java.util.List;

public class Position2Switch extends CommandGroupV2 {

    public Position2Switch(int autoNum) {
        if(autoNum == 0) {
            //LEFT CODE
            List<Path.Waypoint> first_path = new ArrayList<>();
            first_path.add(new Path.Waypoint(new Translation2d(0, 0), 50.0));
            first_path.add(new Path.Waypoint(new Translation2d(18, 0),  50.0));
            first_path.add(new Path.Waypoint(new Translation2d(60,-60),50.0));
            first_path.add(new Path.Waypoint(new Translation2d(100,-60),50.0, "end"));
            first_path.add(new Path.Waypoint(new Translation2d(102,-60),50.0));

            List<Path.Waypoint> second_path = new ArrayList<>();
            second_path.add(new Path.Waypoint(new Translation2d(102,-60),40.0));
            second_path.add(new Path.Waypoint(new Translation2d(60,-60),40.0));

            List<Path.Waypoint> third_path = new ArrayList<>();
            third_path.add(new Path.Waypoint(new Translation2d(60,-60),60.0));
            third_path.add(new Path.Waypoint(new Translation2d(90,-10),60.0));

            List<Path.Waypoint> fourth_path = new ArrayList<>();
            fourth_path.add(new Path.Waypoint(new Translation2d(90,-10),60.0));
            fourth_path.add(new Path.Waypoint(new Translation2d(60,-60),60.0));

            List<Path.Waypoint> fifth_path = new ArrayList<>();
            fifth_path.add(new Path.Waypoint(new Translation2d(50,-60),50.0));
            fifth_path.add(new Path.Waypoint(new Translation2d(100,-60),50.0, "end 2"));
            fifth_path.add(new Path.Waypoint(new Translation2d(108,-60),50.0));

            addParallel(new DropForks(), 2.000);
            addParallel(new FollowPath(new Path(first_path), false), 20.000);
            addSequential(new WaitForPathMarker("end"), 10.000);
            addSequential(new ContingentWait(ContingentWait.Target.Switch), 20.000);
            addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 0.500);

            addParallel(new DropForks(), 1.500);
            addParallel(new LiftToPosition(-850), 3.000);
            addSequential(new FollowPath(new Path(second_path), true), 20.000);

            //addSequential(new DriveStateWait(), 3.000);
            addSequential(new Wait(), 0.2500);

            addSequential(new GyroToAngle(48), 0.500);

            addParallel(new CubeMovement(CubeMovement.CubeState.Pickup), 2.500);
            addSequential(new FollowPath(new Path(third_path), false), 3.000);

            addSequential(new Wait(), 0.500);

            addParallel(new LiftToPosition(700), 2.000);
            addSequential(new FollowPath(new Path(fourth_path), true), 20.000);

            addSequential(new Wait(), 0.500);

            addParallel(new LiftToPosition(150), 1.000);
            addParallel(new FollowPath(new Path(fifth_path), false), 20.000);

            addSequential(new WaitForPathMarker("end 2"), 10.000);
            addSequential(new ContingentWait(ContingentWait.Target.Switch), 20.000);
            addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1.000);
        }
        else{
            //RIGHT CODE
            List<Path.Waypoint> first_path = new ArrayList<>();
            first_path.add(new Path.Waypoint(new Translation2d(0, 0), 50.0));
            first_path.add(new Path.Waypoint(new Translation2d(18, 0), 50.0));
            first_path.add(new Path.Waypoint(new Translation2d(60, 48), 50.0));
            first_path.add(new Path.Waypoint(new Translation2d(100, 48), 50.0, "end"));
            first_path.add(new Path.Waypoint(new Translation2d(102, 48), 50.0));

            List<Path.Waypoint> second_path = new ArrayList<>();
            second_path.add(new Path.Waypoint(new Translation2d(102,48),40.0));
            second_path.add(new Path.Waypoint(new Translation2d(60,48),40.0));

            List<Path.Waypoint> third_path = new ArrayList<>();
            third_path.add(new Path.Waypoint(new Translation2d(60,48),60.0));
            third_path.add(new Path.Waypoint(new Translation2d(88,0),60.0));

            List<Path.Waypoint> fourth_path = new ArrayList<>();
            fourth_path.add(new Path.Waypoint(new Translation2d(88,0),60.0));
            fourth_path.add(new Path.Waypoint(new Translation2d(60,48),60.0));

            List<Path.Waypoint> fifth_path = new ArrayList<>();
            fifth_path.add(new Path.Waypoint(new Translation2d(60,48),50.0));
            fifth_path.add(new Path.Waypoint(new Translation2d(100,48),50.0, "end 2"));
            fifth_path.add(new Path.Waypoint(new Translation2d(116,48),50.0));

            addParallel(new FollowPath(new Path(first_path), false), 20.000);
            addParallel(new DropForks(), 2.000);
            addSequential(new WaitForPathMarker("end"), 10.000);
            addSequential(new ContingentWait(ContingentWait.Target.Switch), 20.000);
            addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 0.500);

            addParallel(new LiftToPosition(-850), 3.000);
            addParallel(new DropForks(), 1.500);
            addSequential(new FollowPath(new Path(second_path), true), 20.000);

            //addSequential(new DriveStateWait(), 3.000);
            addSequential(new Wait(), 0.2500);

            addSequential(new GyroToAngle(-45), 0.500);

            addParallel(new CubeMovement(CubeMovement.CubeState.Pickup), 2.250);
            addSequential(new FollowPath(new Path(third_path), false), 3.000);

            addSequential(new Wait(), 0.500);

            addParallel(new LiftToPosition(700), 2.000);
            addSequential(new FollowPath(new Path(fourth_path), true), 20.000);

            addParallel(new LiftToPosition(850), 1.000);
            addParallel(new FollowPath(new Path(fifth_path), false), 20.000);

            addSequential(new WaitForPathMarker("end 2"), 10.000);
            addSequential(new ContingentWait(ContingentWait.Target.Switch), 20.000);
            addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1.000);
        }
    }
}
