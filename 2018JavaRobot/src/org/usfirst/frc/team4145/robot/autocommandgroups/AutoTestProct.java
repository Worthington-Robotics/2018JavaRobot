package org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.commands.autoonly.*;
import org.usfirst.frc.team4145.robot.commands.testonly.LowerLiftTest;
import org.usfirst.frc.team4145.robot.commands.testonly.MotorBaseTest1;
import org.usfirst.frc.team4145.robot.commands.testonly.RoboLiftTest;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Translation2d;
import org.usfirst.frc.team4145.robot.shared.StateMachineV2.CommandGroupV2;

import java.util.ArrayList;
import java.util.List;

public class AutoTestProct extends CommandGroupV2 {

	public AutoTestProct() {
		List<Path.Waypoint> first_path = new ArrayList<>();
		first_path.add(new Path.Waypoint(new Translation2d(0, 0), 30.0));
		first_path.add(new Path.Waypoint(new Translation2d(15,0), 30.0, "lift"));
		//addSequential(new MotorBaseTest1(), 4020);
		addParallel(new LowerLiftTest(), 20.000);
		addParallel(new FollowPath(new Path(first_path), false), 20.000);
		addParallel(new ForkTest(), 20.000);
		addSequential(new RoboLiftTest(), 20.000);
		//addSequential(new HigherLiftTest(), 1000000);
	}
	

}
