package org.usfirst.frc.team4145.robot.commands.autoonly;

import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.team4145.robot.shared.AccessiblePIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class LiftToPosition extends Command{
	
	private int uppertol = 1;
	private int newcount;	
	private int lowertol = -1;
	
	private PIDController liftpid; 
	private AccessiblePIDOutput output;

	public LiftToPosition(int count){ //
		requires(RobotMap.lift);
		newcount = count;
		output = new AccessiblePIDOutput();
		liftpid = new PIDController(0.1, 0, 0.1, RobotMap.liftEnc,output); // create pid object with parameters
	}
	
	public void initialize(){
		if((RobotMap.liftEnc.get() + uppertol) < newcount || (RobotMap.liftEnc.get() + lowertol) > newcount){  //if outside tolerance, start pid
			liftpid.setSetpoint(newcount); //set target of pid 
			liftpid.setAbsoluteTolerance(0.1); // set tolerance of pid
			liftpid.enable(); //start pid

		}
		else{ //else ignore command
			end();
		}
	}
	public void execute(){
		RobotMap.lift.liftspeedL(output.getValue());
	}

	public boolean isFinished(){
		return liftpid.onTarget(); // If pid is at tolerance
		
	}
	public void end(){
		RobotMap.lift.stopliftL();
		liftpid.disable();// Stop Pid
		liftpid.free(); // Frees sensor and actuator
		// If pid is at tolerance
		}
	public void interrupted(){ // 
		end();
	}	
}