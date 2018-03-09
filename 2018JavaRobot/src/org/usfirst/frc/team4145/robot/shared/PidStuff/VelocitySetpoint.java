package org.usfirst.frc.team4145.robot.shared.PidStuff;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Deprecated
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
    
    public static VelocitySetpoint[] pointArray(String FilePath)
    {
    	try {
    		File CSV = new File(FilePath);
			int i = 0;
			double a = 0, v = 0, d = 0;
			Scanner in = new Scanner(CSV);
			in.useDelimiter(",");
			VelocitySetpoint [] pointArray = new VelocitySetpoint [3000];
			while(in.hasNext())
			{
				if(i%3 == 0)
				{
					d = in.nextDouble();
				}
				if(i%3 == 1)
				{
					v = in.nextDouble();
				}
				if(i%3 == 2)
				{
					in.nextDouble();
					VelocitySetpoint point = new VelocitySetpoint(d,v,a);
					pointArray[i/3] = point;
				}
				i++;
			}
    	}
    	catch(FileNotFoundException error)
    	{

		}
		return null;
    }
}
