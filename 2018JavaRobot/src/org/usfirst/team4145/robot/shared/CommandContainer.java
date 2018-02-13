package src.org.usfirst.team4145.robot.shared;

public class CommandContainer {
	private Command command;
	private boolean isPara;
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
	public boolean getIsPara()
	{
		return isPara;
	}
	public long getCommand()
	{
		return command;
	}

}
