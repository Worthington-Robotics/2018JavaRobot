package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;

import java.util.ArrayList;
import java.util.List;


public class Position3Scale extends CommandGroupV2 {

	public boolean shift = true;

	public Position3Scale(int autonumber) {
		//Right
		if (autonumber == 0) {
			List<Path.Waypoint> first_path = new ArrayList<>();
			first_path.add(new Path.Waypoint(new Translation2d(0, 0), 90.0));
			first_path.add(new Path.Waypoint(new Translation2d(60,0), 90.0, "lift"));
			first_path.add(new Path.Waypoint(new Translation2d(150, 0), 70.0));
			first_path.add(new Path.Waypoint(new Translation2d(200, 0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(218, 0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(250, -15), 40.0, "end"));
			first_path.add(new Path.Waypoint(new Translation2d(256, -21), 40.0));


			List<Path.Waypoint> second_path = new ArrayList<>();
			second_path.add(new Path.Waypoint(new Translation2d(256,-21),40.0));
			second_path.add(new Path.Waypoint(new Translation2d(250,-16),40.0));

			List<Path.Waypoint> third_path = new ArrayList<>();
			third_path.add(new Path.Waypoint(new Translation2d(240,0),60.0));
			third_path.add(new Path.Waypoint(new Translation2d(200,-30),60.0));

			List<Path.Waypoint> fourth_path = new ArrayList<>();
			fourth_path.add(new Path.Waypoint(new Translation2d(200,-30),80.0));
			fourth_path.add(new Path.Waypoint(new Translation2d(268,0),80.0));

			addParallel(new LiftToPosition(600), 1.000);
			addParallel(new FollowPath(new Path(first_path), false), 20.000);
			addSequential(new WaitForPathMarker("lift"));

			addSequential(new HighLiftUp(), 3.000);

			addSequential(new WaitForPathMarker("end"), 10.000);
			addSequential(new ContingentWait(ContingentWait.Target.Scale), 20.000);
			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 0.500);

			addParallel(new LiftToPosition(-800), 1.000);
			addSequential(new FollowPath(new Path(second_path), true), 20.000);

			addParallel(new LiftToPosition(-800), 0.250);
			addParallel(new HighLiftDown(), 0.250);
			addSequential(new Wait(), 0.250);

			addParallel(new LiftToPosition(-800), 1.000);
			addParallel(new HighLiftDown(), 1.000);
			addSequential(new GyroToAngle(-100), 1.000);

			addParallel(new LiftToPosition(-800), 3.000);
			addParallel(new HighLiftDown(), 3.000);
			addParallel(new CubeMovement(CubeMovement.CubeState.Pickup), 3.000);
			addSequential(new FollowPath(new Path(third_path), false), 20.000);

			addParallel(new CubeMovement(CubeMovement.CubeState.Pickup), 0.500);
			addSequential(new Wait(), 0.500);

			addParallel(new LiftToPosition(800), 3.500);
			addParallel(new HighLiftUp(), 3.500);
			//addParallel(new CubeMovement(CubeMovement.CubeState.Pickup), 1.000);
			addSequential(new FollowPath(new Path(fourth_path), true), 20.000);

			addSequential(new GyroToAngle(80), 1.000);

			addSequential(new ContingentWait(ContingentWait.Target.Scale), 20.000);

			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1.000);
		}

		//Left
		if (autonumber == 1) {
			List<Path.Waypoint> first_path = new ArrayList<>();
			first_path.add(new Path.Waypoint(new Translation2d(0, 0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(shift? 191 :209,0), 40.0, "lift"));
			first_path.add(new Path.Waypoint(new Translation2d(shift? 191 :209, -100), 40.0));

			addParallel(new LiftToPosition(600), 1.000);
			addSequential(new FollowPath(new Path(first_path), false), 20.000);
		}
	}
}

