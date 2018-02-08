package org.usfirst.team4145.robot.shared;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class CustomPIDSubsystem extends Subsystem implements PIDSource, PIDOutput{

    public abstract double pidRead();

    public abstract void pidWrite(double output);

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {

    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return null;
    }

    public double pidGet(){
        return pidRead();
    }

}
