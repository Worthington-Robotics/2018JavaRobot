package src.org.usfirst.frc.team4145.robot.autocommandgroups;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.HighLiftUp;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropCube;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.HighLiftUp;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.commands.autoonly.DriveTo;
import org.usfirst.frc.team4145.robot.commands.autoonly.DropForks;
import org.usfirst.frc.team4145.robot.commands.autoonly.GyroToAngle;
import org.usfirst.frc.team4145.robot.commands.autoonly.HighLiftUp;
import org.usfirst.frc.team4145.robot.commands.autoonly.LiftToPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FongSwitch extends CommandGroup {

	public FongSwitch(int autonumber) {
		if (autonumber == 0) {
			//Go forward and do fork stuff
			addParallel(new DriveTo(19 * 24));
			addParallel(new GyroToAngle(RobotMap.drive.getGyro()));
			addParallel(new DropForks);
			addParallel(new LiftToPosition(500));
			
			//Start going forward towards switch
			addsequential(new GyroToAngle(RobotMap.drive.getGyro() + 47));
			addsequential(new DriveTo(19 * 70));
			addParallel(new GyroToAngle(RobotMap.drive.getGyro()))
			
			//Go towards switch and drop cube
			addsequential(new GyroToAngle(RobotMap.drive.getGyro()))
			addsequential(new VisionTarget);
			addsequential(new DriveTo(19 * 36));
			addsequential(new DropCube());
		}
		if (autonumber == 1) {
			//Drive Forward and do fork stuff
			addParallel(new DriveTo(19 * 50));
			addParallel(new DropForks());
			addParallel(new LiftToPosition(500));
			addParallel(new GyroToAngle(RobotMap.drive.getGyro());
			
			//Turn Right and go forward
			addsequential(new GyroToAngle(RobotMap.drive.getGyro() + 90));
			addsequential(new DriveTo(19 * 70));
			addParallel(new GyroToAngle(RobotMap.drive.getGyro())
					
			//Go towards switch and	drop cube
			addsequential (new GyroToAngle(RobotMap.drive.getGyro()))
			addsequential(new VisionTarget());
			addsequential(new DriveTo(19 * 70));
			addParallel(new GyroToAngle(RobotMap.drive.getGyro());
			addsequential(new DropCube());
		}
	}
	
}
