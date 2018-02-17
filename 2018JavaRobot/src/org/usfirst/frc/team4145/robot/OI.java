/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4145.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team4145.robot.commands.teleoponly.*;

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

		Button eleven = new JoystickButton(masterStick, 11);
		eleven.whileHeld(new BotUp());

		Button twelve = new JoystickButton(masterStick,12);
		twelve.whileHeld(new BotDown());

		Button nine = new JoystickButton(masterStick, 9);
		nine.whileHeld(new LowGear());
		
		//operator stick - Attack 3
		secondStick = new Joystick(1);
		
		Button secondStickTwo = new JoystickButton(secondStick, 2);
		secondStickTwo.whileHeld(new Pickup());
		
		Button secondStickThree = new JoystickButton(secondStick, 3);
		secondStickThree.whileHeld(new Release());
		
		Button secondStickFour = new JoystickButton(secondStick, 4);
		secondStickFour.whileHeld(new DropManipulation());
		
		Button secondStickFive = new JoystickButton(secondStick, 5);
		secondStickFive.whileHeld(new LiftManipulation());

		Button secondStickSix = new JoystickButton(secondStick, 6);
		secondStickSix.whileHeld(new LiftButtonDown());
		
		Button secondStickSeven = new JoystickButton(secondStick, 7);
		secondStickSeven.whileHeld(new LiftButtonUp());
		
		Button secondStickEight = new JoystickButton(secondStick, 8);
		secondStickEight.whileHeld(new DropManipulation());
		
		Button secondStickNine = new JoystickButton(secondStick, 9);
		secondStickNine.whileHeld(new LiftManipulation());
	}

	public Joystick getMasterStick() {
		return masterStick;
	}

	public Joystick getSecondStick() {
		return secondStick;
	}
}