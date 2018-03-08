package org.usfirst.frc.team4145.robot.shared.PidStuff;
import java.util.Scanner;
import java.io.file;
public class VelocitySetpoint {

    private double dist, velo, acc;

    public VelocitySetpoint(double distance, double velocity, double acceleration){
        dist = distance; velo = velocity; acc = acceleration;
    }

    public double getAcceleration() {
        return acc;
    }

    public double getDistance() {
        return dist;
    }

    public double getVelocity(){
        return velo;
    }
    
    public static VelocitySetpoint[] pointArray(String x)
    {
    	try {
    	File CSV = new File(x);}
    	catch(IOExeption error)
    	{}
    	int i = 0;
    	int a = 0;
    	int v = 0;
    	int d = 0;
    	Scanner in = new Scanner(CSV);
    	in.setDelimiter(',');
    	VelocitySetpoint [] pointArray = new VelocitySetpoint [3000];
    	while(in.hasNext())
    	{
    		if(i%3 == 0)
    		{
    	VelocitySetpoint point = new VelocitySetpoint(d,v,a);
    	pointArray[i/3] = point;
    	d = in.nextInt();
    	i++;
    		}
    		if(i%3 == 1)
    		{
    	v = in.nextInt();
    	i++;
    		}
    		if(i%3 == 2)
    		{
    	a = in.nextInt();
    	i++;
    		}
    	}
    		
    }
}
