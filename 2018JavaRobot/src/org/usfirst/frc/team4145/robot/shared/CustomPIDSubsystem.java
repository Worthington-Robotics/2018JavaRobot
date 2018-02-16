package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * obfuscates weird method implemented in pid source from end user
 * passes off subsystem and pid input / output methods to be implemented
 * by child class
 */
public abstract class CustomPIDSubsystem extends Subsystem implements PIDSource, PIDOutput{

    private PIDSourceType type = PIDSourceType.kDisplacement;

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        type = pidSource;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return type;
    }

}
