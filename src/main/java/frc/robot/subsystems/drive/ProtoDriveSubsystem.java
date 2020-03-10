/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

class ProtoDriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ProtoDriveSubsystem.class.getName());

    /**
     * Drive Constants
     */
    private static final double s = 0.111; // Volts
    private static final double v = 2.78; // VoltSeconds Per Meter
    private static final double a = 0.13; // VoltSecondsSquared Per Meter
    // private static final double p = 4.29; // Drive Velocity
    private static final double p = 0;
    private static final double trackWidth = 0.61; // Meters
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;
    private static final double maxSpeed = 3.04; // Meters Per Second
    private static final double maxAcceleration = 0.5; // Meters Per Second Squared
    private static final boolean leftReversed = false;
    private static final boolean rightReversed = false;
    private static final double wheelRadius = 0.1524; // Meters
    private static final double beltGearing = 1;
    private static final double gearboxGearing = 10.71; // Standard AndyMark KoP chassis Toughbox Mini gearing

    // Voltage constraint for trajectory following
    private final DifferentialDriveVoltageConstraint autoVoltageConstraint;

    // Trajectory configuration for trajectory following
    private final TrajectoryConfig trajectoryConfig;

    /**
     * Mechanisms and Sensors
     */

    private final CANSparkMax leftFrontMotor;
    private final CANSparkMax leftRearMotor;
    private final CANSparkMax rightFrontMotor;
    private final CANSparkMax rightRearMotor;

    private final CANEncoder leftEncoder;
    private final CANEncoder rightEncoder;

    private final CANPIDController leftPIDController;
    private final CANPIDController rightPIDController;

    private final IGyroSensor nav;

    private final DifferentialDrive drive;
    private final DifferentialDriveKinematics driveKinematics;
    private final DifferentialDriveOdometry driveOdometry;
    private final RamseteController ramseteController;

    private final SimpleMotorFeedforward feedforward;

    private final DriveHelper helper;

    ProtoDriveSubsystem() {
        logger.info("constructing");

        leftFrontMotor = new CANSparkMax(20, MotorType.kBrushless);
        leftFrontMotor.restoreFactoryDefaults();
        leftRearMotor = new CANSparkMax(21, MotorType.kBrushless);
        leftRearMotor.restoreFactoryDefaults();
        rightFrontMotor = new CANSparkMax(23, MotorType.kBrushless);
        rightFrontMotor.restoreFactoryDefaults();
        rightRearMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightRearMotor.restoreFactoryDefaults();

        leftFrontMotor.setInverted(false);
        rightFrontMotor.setInverted(false);

        leftRearMotor.follow(leftFrontMotor);
        rightRearMotor.follow(rightFrontMotor);

        leftEncoder = new CANEncoder(leftFrontMotor);
        rightEncoder = new CANEncoder(rightFrontMotor);

        leftPIDController = new CANPIDController(leftFrontMotor);
        leftPIDController.setP(p);
        rightPIDController = new CANPIDController(rightFrontMotor);
        rightPIDController.setP(p);

        nav = GyroFactory.getInstance();

        drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
        drive.setSafetyEnabled(false);
        driveKinematics = new DifferentialDriveKinematics(trackWidth);
        driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(nav.getAngle()));
        ramseteController = new RamseteController(ramseteB, ramseteZeta);

        feedforward = new SimpleMotorFeedforward(s, v, a);

        autoVoltageConstraint = new DifferentialDriveVoltageConstraint(feedforward, driveKinematics, 10);

        trajectoryConfig = new TrajectoryConfig(maxSpeed, maxAcceleration).setKinematics(driveKinematics)
                .addConstraint(autoVoltageConstraint);

        helper = new DriveHelper();

        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
        driveOdometry.resetPosition(new Pose2d(0, 0, new Rotation2d(0)), new Rotation2d(0));

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        driveOdometry.update(Rotation2d.fromDegrees(nav.getAngle()), leftEncoder.getPosition(),
                rightEncoder.getPosition());
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Drive.distanceMeters,
                convertEncoderClicksToMeters(leftEncoder.getPosition()));
    }

    @Override
    public void stop() {
        drive.tankDrive(0, 0);
    }

    @Override
    public void setBrake(boolean brakeOn) {
        if (brakeOn) {
            leftFrontMotor.setIdleMode(IdleMode.kBrake);
            leftRearMotor.setIdleMode(IdleMode.kBrake);
            rightFrontMotor.setIdleMode(IdleMode.kBrake);
            rightRearMotor.setIdleMode(IdleMode.kBrake);
        } else {
            leftFrontMotor.setIdleMode(IdleMode.kCoast);
            leftRearMotor.setIdleMode(IdleMode.kCoast);
            rightFrontMotor.setIdleMode(IdleMode.kCoast);
            rightRearMotor.setIdleMode(IdleMode.kCoast);
        }
    }

    private static double speed;
    private static double turn;
    private static double leftSpeed;
    private static double rightSpeed;

    private final double quickTurnThreshold = 0.2;

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        // Save off passed values for telemetry
        speed = hmiSpeed;
        turn = hmiTurn;

        boolean quickTurn = (Math.abs(speed) < quickTurnThreshold);
        DriveSignal driveSignal = helper.cheesyDrive(speed, turn, quickTurn, false);

        arcadeDrive(driveSignal);
    }

    private void arcadeDrive(DriveSignal driveSignal) {
        // Save values for telemetry
        leftSpeed = driveSignal.getLeft();
        rightSpeed = driveSignal.getRight();

        leftFrontMotor.set(leftSpeed);
        rightFrontMotor.set(rightSpeed);
    }

    @Override
    public void followPath(final Pose2d start, final List<Translation2d> interiorWaypoints, final Pose2d end) {

        // Create trajectory to follow
        final Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, end,
                trajectoryConfig);

        // Schedule a RamseteCommand that executes the necessary trajectory
        CommandScheduler.getInstance().schedule(new RamseteCommand(trajectory, this::getPose, ramseteController,
                driveKinematics, this::setPIDVelocity, this));

    }

    private void setPIDVelocity(double leftVelocity, double rightVelocity) {
        leftPIDController.setFF(feedforward.calculate(leftVelocity));
        rightPIDController.setFF(feedforward.calculate(rightVelocity));

        leftPIDController.setReference(feedforward.calculate(leftVelocity), ControlType.kVelocity);
        rightPIDController.setReference(feedforward.calculate(rightVelocity), ControlType.kVelocity);
    }

    protected double convertEncoderClicksToMeters(double encoderClicks) {
        return encoderClicks * (1 / gearboxGearing) * beltGearing * (2 * Math.PI * wheelRadius) / 2;
    }

    protected double convertInchesToEncoderClicks(double inches) {
        return inches * (1 / 12) // Conversion to feet
                * 3.281 // Conversion to meters
                * (1 / (2 * Math.PI * wheelRadius)) // Convert to wheel revolutions (Circumference)
                * (beltGearing) // Convert to output shaft revolutions (Belt gearing)
                * (1 / gearboxGearing); // Convert to motor revolutions (TB Mini gearing)
    }

    /*
     * RAMSETE Methods
     */

    /**
     * 
     * @param leftVolts
     * @param rightVolts
     */
    private void tankDriveVolts(final double leftVolts, final double rightVolts) {
        leftFrontMotor.setVoltage(leftVolts * (leftReversed ? -1 : 1));
        rightFrontMotor.setVoltage(rightVolts * (rightReversed ? -1 : 1));
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    private Pose2d getPose() {
        return driveOdometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    private DifferentialDriveWheelSpeeds getVelocity() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void swap() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getEncoderClicks() {
        // TODO Auto-generated method stub
        return 0;
    }

}