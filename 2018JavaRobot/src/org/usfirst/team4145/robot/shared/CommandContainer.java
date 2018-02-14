package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.command.Command;

public class CommandContainer {
	private Command command;
	private boolean isPara; // is parallel with previous command
	private long timeout = -1;
	
	public CommandContainer(Command com, boolean IP)
	{
		command = com;
		isPara = IP;
		timeout = -1;
		
	}
	public CommandContainer(Command com, boolean IP, long time)
	{
		command = com;
		isPara = IP;
		timeout = time;
		
	}
	public long getTimeout()
	{
		return timeout;
	}

	/**
	 * gets whether or not the command is parallel with previous
	 * @return is parallel with previous
	 */
	public boolean getIsPara()
	{
		return isPara;
	}

	public Command getCommand()
	{
		return command;
	}

	public boolean hasExceededTimeout(){
		return command.timeSinceInitialized() >= timeout;
	}

}
