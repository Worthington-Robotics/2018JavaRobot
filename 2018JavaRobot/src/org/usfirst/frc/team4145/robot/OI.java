/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import org.usfirst.frc.team4145.robot.commands.DropManipulation;
import org.usfirst.frc.team4145.robot.commands.FlipRef;
import org.usfirst.frc.team4145.robot.commands.GyroLock;
import org.usfirst.frc.team4145.robot.commands.LiftButtonDown;
import org.usfirst.frc.team4145.robot.commands.LiftButtonUp;
import org.usfirst.frc.team4145.robot.commands.LiftManipulation;
import org.usfirst.frc.team4145.robot.commands.Pickup;
import org.usfirst.frc.team4145.robot.commands.Release;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.

	private Joystick masterStick;
	private Joystick secondStick;

	public OI() {
		//driver stick - Extreme 3d pro
		masterStick = new Joystick(0);

		Button trigger = new JoystickButton(masterStick, 1);
		trigger.whileHeld(new GyroLock());

		Button thumb = new JoystickButton(masterStick, 2);
		thumb.whileHeld(new FlipRef());
		
		
		//operator stick - Attack 3
		secondStick = new Joystick(1);
		
		Button two = new JoystickButton(secondStick, 2);
		two.whileHeld(new Pickup());
		
		Button three = new JoystickButton(secondStick, 3);
		three.whileHeld(new Release());
		
		Button four = new JoystickButton(secondStick, 4);
		four.whileHeld(new DropManipulation());
		
		Button five = new JoystickButton(secondStick, 5);
		five.whileHeld(new LiftManipulation());

		Button sev = new JoystickButton(secondStick, 7);
		sev.whileHeld(new LiftButtonUp());

		Button six = new JoystickButton(secondStick, 6);
		six.whileHeld(new LiftButtonDown());
	}

	public Joystick getMasterStick() {
		return masterStick;
	}

	public Joystick getSecondStick() {
		return secondStick;
	}
}