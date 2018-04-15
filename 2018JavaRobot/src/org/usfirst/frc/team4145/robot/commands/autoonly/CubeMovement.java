package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.Constants;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.subsystems.CubeManipulation;



public class CubeMovement extends Command{

    public enum CubeState{

        Shoot(Constants.CUBEMANIP_SHOOT),
        RollOut(Constants.CUBEMANIP_FAST_DROP),
        Drop(Constants.CUBEMANIP_DROP),
        Pickup(Constants.CUBEMANIP_PICKUP),
        Stop(0);

        private double shotPower;

        CubeState(double power){
            shotPower = power;
        }

        public double getShotPower() {
            return shotPower;
        }
    }

    private CubeState manipState;

    public CubeMovement(CubeState state) {
        requires(RobotMap.cubeManipulator);
        manipState = state;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public void initialize() {
        // starts motors y'all
        RobotMap.cubeManipulator.setPower(manipState.getShotPower());
        System.out.println("Cube Movement Init");
    }

    public void end() {
        RobotMap.cubeManipulator.stall();
        System.out.println("Cube Movement End");
    }

    public void interrupted() {
        end();
    }

}
