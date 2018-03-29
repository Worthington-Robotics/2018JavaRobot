package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;


public class ContingentWait extends Command {

    public enum Target{
        Switch(0),
        Scale(1);

        private int charPos;

        Target(int pos){
            charPos = pos;
        }

        public int getPos(){
            return charPos;
        }

    }

    private String dataAtStart;
    private Target objective;

    public ContingentWait(Target objective) {
        this.objective = objective;
    }

    protected void initialize() {
        dataAtStart = RobotMap.gameDataAtStart;
    }

    protected boolean isFinished() {
        String currentdata = DriverStation.getInstance().getGameSpecificMessage();
        DriverStation.reportWarning("current data: " + currentdata + " past data: " + dataAtStart, false);
        return dataAtStart.charAt(objective.getPos()) == currentdata.charAt(objective.getPos());
    }


    protected void end() {

    }

    protected void interrupted() {
        end();
    }
}
