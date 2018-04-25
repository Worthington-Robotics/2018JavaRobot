package org.usfirst.frc.team4145.robot.subsystems.RobotDriveV4;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.*;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.*;
import org.usfirst.frc.team4145.robot.shared.MixedDrive;

import java.util.Set;

public class RobotDriveV4 extends Subsystem implements PIDOutput, PIDSource {

    enum DriveControlState {
        OPEN_LOOP("Open Loop"), PATH_FOLLOWING_CONTROL("Path following"), PROFILING_TEST("Profiling test");
        private String s;

        DriveControlState(String name){
            s = name;
        }

        @Override
        public String toString() {
            return s;
        }
    }

    //used internally for data
    private MixedDrive m_MixedDriveInstance;
    private Notifier m_NotifierInstance;
    private PIDController gyroLock;
    private double pidOutput = 0, lastval = 0; //DO NOT MODIFY
    private boolean enLock = false, isReversed = false;
    private double[] operatorInput = {0, 0, 0}; //last input set from joystick update
    private PIDSourceType type = PIDSourceType.kDisplacement;
    private DriveControlState driveControlState = DriveControlState.OPEN_LOOP;
    private AdaptivePurePursuitController pathFollowingController;

    public RobotDriveV4() {
        m_MixedDriveInstance = new MixedDrive(RobotMap.driveFrontLeft, RobotMap.driveRearLeft, RobotMap.driveFrontRight, RobotMap.driveRearRight);
        m_NotifierInstance = new Notifier(periodic);
        reset();
        initGyro();
        startPeriodic();
    }

    private void startPeriodic(){
        m_NotifierInstance.startPeriodic(Constants.DRIVETRAIN_UPDATE_RATE);
    }

    private Runnable periodic = () -> {
        synchronized (RobotDriveV4.this){
            if(Constants.ENABLE_MP_TEST_MODE) driveControlState = DriveControlState.PROFILING_TEST;
            if(DriverStation.getInstance().isEnabled()){
                switch (driveControlState){
                    case PATH_FOLLOWING_CONTROL:
                        updatePathFollower();
                        if (isFinishedPath()) stop();
                        break;

                    case PROFILING_TEST:
                        if(DriverStation.getInstance().isAutonomous()) {
                            driveTank(Constants.MP_TEST_SPEED, Constants.MP_TEST_SPEED);
                        }
                        break;

                    default: //open loop
                        if(DriverStation.getInstance().isOperatorControl())operatorInput = getAdjStick();
                        else operatorInput = new double[] {0,0,0};
                        if (isReversed) {
                            operatorInput[0] *= -1;
                            operatorInput[1] *= -1;
                        }
                        if (Robot.oi.getMasterStick().getPOV() >= 0) {
                            operatorInput[0] *= Constants.getTeleopYCutPercentage();
                            operatorInput[1] *= Constants.getTeleopXCutPercentage();
                        }
                        if (enLock) operatorInput[2] = pidOutput;
                        else setTarget(getGyro()); // Safety feature in case PID gets enabled
                        driveCartesian(operatorInput[1], -operatorInput[0], operatorInput[2]);
                        break;
                }
            }
            smartDashboardUpdates();
        }
    };

    public double getGyro() {
        return ((RobotMap.ahrs.getYaw() + 360) % 360); //add 360 to make all positive then mod by 360 to get remainder
    }

    public double getGyroContinuous(){
        return RobotMap.ahrs.getAngle();
    }

    public double getLeftEncoder(){
        return -RobotMap.driveFrontLeft.getSensorCollection().getQuadraturePosition();
    }

    public double getLeftVelocity(){
        return -RobotMap.driveFrontLeft.getSensorCollection().getQuadratureVelocity();
    }

    public double getRightEncoder(){
        return RobotMap.driveFrontRight.getSensorCollection().getQuadraturePosition();
    }

    public double getRightVelocity(){
        return RobotMap.driveFrontRight.getSensorCollection().getQuadratureVelocity();
    }

    public synchronized void followPath(Path path, boolean reversed) {
        pathFollowingController = new AdaptivePurePursuitController(Constants.PATH_FOLLOWING_LOOKAHEAD,
                Constants.PATH_FOLLOWING_MAX_ACCELERATION, Constants.DRIVETRAIN_UPDATE_RATE, path, reversed, 1);
        driveControlState = DriveControlState.PATH_FOLLOWING_CONTROL;
        updatePathFollower();
    }

    public synchronized Set<String> getPathMarkersCrossed() {
        if (pathFollowingController == null) {
            return null;
        } else {
            return pathFollowingController.getMarkersCrossed();
        }
    }

    public void stop(){
        driveControlState = DriveControlState.OPEN_LOOP;
        driveTank(-Math.signum(lastval) * Constants.BRAKE_RPM, -Math.signum(lastval) * Constants.BRAKE_RPM);
    }

    public synchronized boolean isFinishedPath() {
        return (driveControlState == DriveControlState.PATH_FOLLOWING_CONTROL && pathFollowingController.isDone())
                || driveControlState != DriveControlState.PATH_FOLLOWING_CONTROL;
    }

    public boolean isOpenLoop(){
        return driveControlState == DriveControlState.OPEN_LOOP;
    }


    public double pidGet(){
        return getGyro();
    }

    public void pidWrite(double output){
        pidOutput = output;
    }

    public void setOperatorInput(double[] input){
        operatorInput = input;
    }

    public void setIsReversed(boolean isReversed){
        this.isReversed = isReversed;
    }

    public void enableTo(double rot, boolean en) {
        this.setTarget(rot);
        this.enableLock(en);
    }

    public void setDynamicBrakeMode(boolean brakeFL, boolean brakeRL, boolean brakeFR, boolean brakeRR) {
        m_MixedDriveInstance.setDynamicBrakeMode(brakeFL,brakeRL, brakeFR, brakeRR);
    }

    public void configTeleop(){
        reset();
        RobotMap.driveFrontLeft.set(ControlMode.PercentOutput, 0);
        RobotMap.driveFrontRight.set(ControlMode.PercentOutput, 0);
        RobotMap.driveRearLeft.set(ControlMode.PercentOutput, 0);
        RobotMap.driveRearRight.set(ControlMode.PercentOutput, 0);
    }

    public void configAuto() {
        reset();
        m_MixedDriveInstance.configureVelocityPIDF(MixedDrive.DriveSelector.FRONT_LEFT, Constants.getLeftKP(),
                Constants.getLeftKI(), Constants.getLeftKD(), Constants.getLeftKF());
        m_MixedDriveInstance.configureVelocityPIDF(MixedDrive.DriveSelector.FRONT_RIGHT, Constants.getRightKP(),
                Constants.getRightKI(), Constants.getRightKD(), Constants.getRightKF());

        RobotMap.driveRearLeft.set(ControlMode.Follower, RobotMap.driveFrontLeft.getDeviceID());
        RobotMap.driveRearRight.set(ControlMode.Follower, RobotMap.driveFrontRight.getDeviceID());
    }

    public void reset(){
        m_MixedDriveInstance.resetSelectedSensors();
        resetGyro();
    }


    private void resetGyro(){
        RobotMap.ahrs.reset();
    }

    private void smartDashboardUpdates() {
        SmartDashboard.putNumber("FPGA Time", Timer.getFPGATimestamp());
        SmartDashboard.putNumber("Gyro Angle", getGyro());
        SmartDashboard.putNumber("Gyro Target", gyroLock.getSetpoint());
        SmartDashboard.putNumber("Right Wheel Encoder", getRightEncoder());
        SmartDashboard.putNumber("Left Wheel Encoder", getLeftEncoder());
        SmartDashboard.putNumber("Right Wheel Distance", getRightEncoder() * ((Constants.WHEEL_DIAMETER * Math.PI) / Constants.COUNTS_PER_REV));
        SmartDashboard.putNumber("Left Wheel Distance", getLeftEncoder() * ((Constants.WHEEL_DIAMETER * Math.PI) / Constants.COUNTS_PER_REV));
        SmartDashboard.putNumber("Right Wheel Velocity", uPer100MsToRPM(getRightVelocity()));
        SmartDashboard.putNumber("Left Wheel Velocity", uPer100MsToRPM(getLeftVelocity()));
        SmartDashboard.putString("Drive Control Mode", driveControlState.toString());
        SmartDashboard.putBoolean("Path Finished", isFinishedPath());
        SmartDashboard.putNumber("Continuous gyro heading", getGyroContinuous());
        SmartDashboard.putNumberArray("operator input", operatorInput);
        if(pathFollowingController != null) SmartDashboard.putString("Markers passed", pathFollowingController.getMarkersCrossed().toString());
    }

    private static double inchesToRotations(double inches) {
        return inches / (Constants.WHEEL_DIAMETER * Math.PI);
    }

    private static double inchesPerSecondToRpm(double inches_per_second) {
        return inchesToRotations(inches_per_second) * 60;
    }

    private static double uPer100MsToRPM(double uPer100Ms){
        return (uPer100Ms* 75) / 512.0;
    }

    private static double RPMToUnitsPer100Ms(double RPM){
        return (RPM * 512) / 75.0;
    }

    private synchronized void updatePathFollower() {
        RigidTransform2d robot_pose = RobotMap.robotPose.getLatestFieldToVehicle().getValue();
        RigidTransform2d.Delta command = pathFollowingController.update(robot_pose, Timer.getFPGATimestamp());
        Kinematics.DriveVelocity setpoint = Kinematics.inverseKinematics(command);

        // Scale the command to respect the max velocity limits
        double max_vel = 0.0;
        max_vel = Math.max(max_vel, Math.abs(setpoint.left));
        max_vel = Math.max(max_vel, Math.abs(setpoint.right));
        if (max_vel > Constants.PATH_FOLLOWING_MAX_VELOCITY) {
            double scaling = Constants.PATH_FOLLOWING_MAX_VELOCITY / max_vel;
            setpoint = new Kinematics.DriveVelocity(setpoint.left * scaling, setpoint.right * scaling);
        }
        driveTank(inchesPerSecondToRpm(setpoint.left), inchesPerSecondToRpm(setpoint.right));
    }

    private void initGyro(){
        gyroLock = new PIDController(Constants.getGyrolockKp(), Constants.getGyrolockKi(), Constants.getGyrolockKd(), this, this);
        gyroLock.setAbsoluteTolerance(Constants.getGyrolockTol());
        gyroLock.setOutputRange(-Constants.getGyrolockLim(), Constants.getGyrolockLim());
        gyroLock.setInputRange(0, 360);
        gyroLock.setContinuous();
    }


    /**
     * method for driving in meccanum cartesian mode
     * @param ySpeed speed scalar (-1,1)
     * @param xSpeed speed scalar (-1,1)
     * @param zRotation (speed scalar) (-1,1)
     */
    private void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
        m_MixedDriveInstance.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    /**
     * method for driving in tank mode
     * @param leftSpeed left speed in RPM
     * @param rightSpeed right speed in RPM
     */
    private void driveTank(double leftSpeed, double rightSpeed) {
        SmartDashboard.putNumberArray("Tank/ RPMS", new double[]{leftSpeed, rightSpeed});
        lastval = leftSpeed;
        SmartDashboard.putNumberArray("Tank/ U MS", new double[]{RPMToUnitsPer100Ms(leftSpeed), RPMToUnitsPer100Ms(rightSpeed)});
        m_MixedDriveInstance.tankDrive(RPMToUnitsPer100Ms(leftSpeed) , RPMToUnitsPer100Ms(rightSpeed));
    }

    private void setTarget(double target) {
        gyroLock.setSetpoint(target);
    }

    private void enableLock(boolean en) {
        enLock = en;
        if (enLock) gyroLock.enable();
        else gyroLock.disable();
    }

    private double[] getAdjStick() {
        double[] out = new double[3];
        out[0] = evalDeadBand(Robot.oi.getMasterStick().getY(), Constants.getTeleopDeadband()) * Constants.getTeleopYPercentage();
        out[1] = evalDeadBand(Robot.oi.getMasterStick().getX(), Constants.getTeleopDeadband()) * Constants.getTeleopXPercentage();
        out[2] = evalDeadBand(Robot.oi.getMasterStick().getZ(), Constants.getTeleopDeadband()) * Constants.getTeleopZPercentage();
        return out;
    }

    // figures out if the stick value is within the deadband
    private double evalDeadBand(double stickInpt, double deadBand) {
        if (Math.abs(stickInpt) < deadBand) {
            return 0;
        } else {
            if (stickInpt < 0) {
                return (0 - Math.pow(stickInpt, 2));
            } else {
                return Math.pow(stickInpt, 2);
            }
        }
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        type = pidSource;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return type;
    }

    protected void initDefaultCommand() {

    }

}
