package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;

import java.util.ArrayList;
import java.util.List;


public class Position3Scale extends CommandGroupV2 {


	public Position3Scale(int autonumber) {
		//Right
		if (autonumber == 0) {
			List<Path.Waypoint> first_path = new ArrayList<>();
			first_path.add(new Path.Waypoint(new Translation2d(0, 0), 90.0));
			first_path.add(new Path.Waypoint(new Translation2d(60,0), 90.0, "lift"));
			first_path.add(new Path.Waypoint(new Translation2d(150, 0), 70.0));
			first_path.add(new Path.Waypoint(new Translation2d(200, 0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(218, 0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(250, -13), 40.0, "end"));
			first_path.add(new Path.Waypoint(new Translation2d(260, -16), 40.0));


			List<Path.Waypoint> second_path = new ArrayList<>();
			second_path.add(new Path.Waypoint(new Translation2d(256,-21),40.0));
			second_path.add(new Path.Waypoint(new Translation2d(250,-16),40.0));

			List<Path.Waypoint> third_path = new ArrayList<>();
			third_path.add(new Path.Waypoint(new Translation2d(240,0),60.0));
			third_path.add(new Path.Waypoint(new Translation2d(215,-33),60.0));

			List<Path.Waypoint> fourth_path = new ArrayList<>();
			fourth_path.add(new Path.Waypoint(new Translation2d(215,-33),80.0));
			fourth_path.add(new Path.Waypoint(new Translation2d(296,-8),80.0));

			addParallel(new LiftToPosition(600), 1.000);
			addParallel(new FollowPath(new Path(first_path), false), 20.000);
			addSequential(new WaitForPathMarker("lift"));

			addParallel(new DropForks(), 3.000);
			addSequential(new HighLiftUp(), 3.000);

			addParallel(new DropForks(), 0.7500);
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
			addSequential(new GyroToAngle(-110), 1.000);

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

		//Left - no delivery
		if (autonumber == 1) {
			List<Path.Waypoint> first_path = new ArrayList<>();
			first_path.add(new Path.Waypoint(new Translation2d(0, 0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(Constants.SHIFT? 191 :209,0), 40.0, "lift"));
			first_path.add(new Path.Waypoint(new Translation2d(Constants.SHIFT? 191 :209, -100), 40.0));

			addParallel(new LiftToPosition(600), 1.000);
			addSequential(new FollowPath(new Path(first_path), false), 20.000);
		}

		if(autonumber == 2){
			List<Path.Waypoint> first_path = new ArrayList<>();
			first_path.add(new Path.Waypoint(new Translation2d(0, 0), 60.0));
			first_path.add(new Path.Waypoint(new Translation2d(180, 0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(209,0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(209,-100), 50.0, "lift"));
			first_path.add(new Path.Waypoint(new Translation2d(209, -190), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(248, -200), 40.0, "end"));
			first_path.add(new Path.Waypoint(new Translation2d(250, -200), 40.0));

			List<Path.Waypoint> second_path = new ArrayList<>();
			second_path.add(new Path.Waypoint(new Translation2d(250,-200),40.0));
			second_path.add(new Path.Waypoint(new Translation2d(230,-200),40.0));

			addParallel(new LiftToPosition(600), 1.000);
			addParallel(new FollowPath(new Path(first_path), false), 20.000);
			addSequential(new WaitForPathMarker("lift"));

			addSequential(new HighLiftUp(), 3.000);

			addSequential(new WaitForPathMarker("end"), 10.000);

			addSequential(new GyroToAngle(20), 1.000);

			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 1.000);

			addSequential(new FollowPath(new Path(second_path), true), 20.000);

			addParallel(new LiftToPosition(-800), 5.000);
			addParallel(new HighLiftDown(), 5.000);
			addSequential(new GyroToAngle(180), 5.000);

		}
		if(autonumber == 3){
			List<Path.Waypoint> first_path = new ArrayList<>();
			first_path.add(new Path.Waypoint(new Translation2d(0, 0), 100.0));
			first_path.add(new Path.Waypoint(new Translation2d(108, 0), 70.0));
			first_path.add(new Path.Waypoint(new Translation2d(180,0), 40.0));
			first_path.add(new Path.Waypoint(new Translation2d(205,-28), 40.0));

			List<Path.Waypoint> second_path = new ArrayList<>();
			second_path.add(new Path.Waypoint(new Translation2d(205,-28),40.0));
			second_path.add(new Path.Waypoint(new Translation2d(177,-56),40.0));

			List<Path.Waypoint> third_path = new ArrayList<>();
			third_path.add(new Path.Waypoint(new Translation2d(177,-56),40.0));
			third_path.add(new Path.Waypoint(new Translation2d(190,-45),40.0));

			List<Path.Waypoint> fourth_path = new ArrayList<>();
			fourth_path.add(new Path.Waypoint(new Translation2d(190,-45),40.0));
			fourth_path.add(new Path.Waypoint(new Translation2d(174,-56),40.0));

			List<Path.Waypoint> fifth_path = new ArrayList<>();
			fifth_path.add(new Path.Waypoint(new Translation2d(187,-47),80.0));
			fifth_path.add(new Path.Waypoint(new Translation2d(187,-100),80.0));

			addParallel(new LiftToPosition(600), 1.000);
			addSequential(new FollowPath(new Path(first_path), false), 20.000);

			addSequential(new GyroToAngle(-72), 1.000);

			addSequential(new FollowPath(new Path(second_path), false), 2.000);

			addSequential(new CubeMovement(CubeMovement.CubeState.Shoot), 0.500);

			addParallel(new LiftToPosition(-800), 3.000);
			addParallel(new FollowPath(new Path(third_path), true), 2.000);
			addSequential(new Wait(), 2.000);

			addParallel(new LiftToPosition(-800), 2.000);
			addParallel(new CubeMovement(CubeMovement.CubeState.Pickup), 1.500);
			addSequential(new FollowPath(new Path(fourth_path), false), 2.000);

			addParallel(new LiftToPosition(800), 1.000);
			addSequential(new GyroToAngle(60), 1.00);

			addParallel(new LiftToPosition(800), 2.000);
			addSequential(new FollowPath(new Path(fifth_path), false), 4.000);

		}
	}
}

