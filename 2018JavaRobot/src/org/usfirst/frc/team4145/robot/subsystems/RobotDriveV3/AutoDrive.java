package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV3;

import edu.wpi.first.wpilibj.PIDController;

public class AutoDrive {

    //data objects / fields
    private PIDController leftVelocity;
    private PIDController rightVelocity;
    private double[] outputArray = {0,0};

    //Shared PID Constants
    private double VELOCITY_PROPORTIONAL = 0.0000;
    private double MAX_VELOCITY = 4.0000; //counts per second
    private double MAX_ACELLERATION = 2.0000; //counts per second per second


    //Left PID constants
    private double LEFT_PROPORTIONAL_GAIN = 0.0000;
    private double LEFT_INTEGRAL_GAIN = 0.0000;
    private double LEFT_DERIVATIVE_GAIN = 0.0000;
    private double LEFT_FEEDFORWARD = 0.0000;


    //Right PID constants

    AutoDrive(){
        //TODO finish class declaration
    }

    public void pidWriteLeft(double output){
        outputArray[0] = output * VELOCITY_PROPORTIONAL;
    }

    public void pidWriteRight(double output){
        outputArray[1] = output * VELOCITY_PROPORTIONAL;
    }

    public double[] update(){
        //TODO implement update method for tank drive from pathing algorithm
        return outputArray;
    }

}
