package org.usfirst.frc.team4145.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.commands.teleoponly.DisableLim;
import org.usfirst.frc.team4145.robot.commands.teleoponly.EnableLim;


public class LimOverride extends Trigger {
    public LimOverride() {
        whenActive(new DisableLim());
        whenInactive(new EnableLim());
    }

    public boolean get() {
        return SmartDashboard.getBoolean("DB/Button 0", false);
    }
}
