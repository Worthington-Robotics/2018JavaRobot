package org.usfirst.frc.team4145.robot.shared.PidStuff;

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
}
