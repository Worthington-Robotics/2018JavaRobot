package org.usfirst.frc.team4145.robot.shared;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class MixedDrive extends MecanumDrive{

    private SpeedController kFrontLeft;
    private SpeedController kFrontRight;
    private SpeedController kRearLeft;
    private SpeedController kRearRight;

    public MixedDrive (SpeedController frontLeft, SpeedController rearLeft, SpeedController frontRight,  SpeedController rearRight){
        super(frontLeft, rearLeft, frontRight, rearRight);
        kFrontLeft = frontLeft;
        kFrontRight = frontRight;
        kRearLeft = rearLeft;
        kRearRight = rearRight;
        setName("MecanumDifferentialDrive ");
    }

    public void tankDrive(double leftSpeed, double rightSpeed, boolean squaredInputs) {

        leftSpeed = limit(leftSpeed);
        leftSpeed = applyDeadband(leftSpeed, m_deadband);

        rightSpeed = limit(rightSpeed);
        rightSpeed = applyDeadband(rightSpeed, m_deadband);

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        if (squaredInputs) {
            leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
            rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);
        }

        kRearLeft.set(leftSpeed * m_maxOutput);
        kFrontLeft.set(leftSpeed * m_maxOutput);
        kFrontRight.set(-rightSpeed * m_maxOutput);
        kRearRight.set(-rightSpeed * m_maxOutput);

        m_safetyHelper.feed();
    }

}
