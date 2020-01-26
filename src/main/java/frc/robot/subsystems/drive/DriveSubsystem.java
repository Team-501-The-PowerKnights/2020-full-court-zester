/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.telemetry.TelemetryNames;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase implements IDriveSubsystem {

    private static final String myName = TelemetryNames.Drive.name;

    private static DriveSubsystem ourInstance;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Drive.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new DriveSubsystem();

        SmartDashboard.putBoolean(TelemetryNames.Drive.status, true);
    }

    public static DriveSubsystem getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    /**
     * Drive Constants
     */
    private static final double kS = 0.16; // Volts
    private static final double kV = 2.73; // VoltSeconds Per Meter
    private static final double kA = 0.32; // VoltSecondsSquared Per Meter
    private static final double kP = 2.55; // Drive Velocity
    private static final double trackWidth = 0.568; // Meters
    private static final double kRamseteB = 2;
    private static final double kRamseteZeta = 0.7;
    private static final double kMaxSpeed = 3.04; // Meters Per Second
    private static final double kMaxAcceleration = 0.5; // Meters Per Second Squared
    private static final boolean kGyroReversed = true;
    private static final boolean kLeftReversed = false;
    private static final boolean kRightReversed = false;
    private static final double kWheelRadius = 0.1524; // Meters
    private static final double kBeltGearing = 1;
    private static final double kGearboxGearing = 10.71; // Standard AndyMark KoP chassis Toughbox Mini gearing

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

    private final SpeedControllerGroup left;
    private final SpeedControllerGroup right;

    private final CANEncoder leftEncoder;
    private final CANEncoder rightEncoder;

    private final DifferentialDrive drive;
    public DifferentialDriveKinematics driveKinematics;
    public DifferentialDriveOdometry driveOdometry;

    private final ADIS16448_IMU nav;

    public DriveSubsystem() {
        leftFrontMotor = new CANSparkMax(23, MotorType.kBrushless);
        leftRearMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightFrontMotor = new CANSparkMax(20, MotorType.kBrushless);
        rightRearMotor = new CANSparkMax(21, MotorType.kBrushless);

        left = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
        right = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);

        left.setInverted(false);
        right.setInverted(true);

        leftEncoder = new CANEncoder(leftFrontMotor);
        rightEncoder = new CANEncoder(rightFrontMotor);

        nav = new ADIS16448_IMU();

        drive = new DifferentialDrive(left, right);
        driveKinematics = new DifferentialDriveKinematics(trackWidth);
        driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(nav.getAngle()));

        autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(kS, kV, kA),
                driveKinematics, 10);

        trajectoryConfig = new TrajectoryConfig(kMaxSpeed, kMaxAcceleration).setKinematics(driveKinematics)
                .addConstraint(autoVoltageConstraint);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        driveOdometry.update(Rotation2d.fromDegrees(nav.getAngle()), leftEncoder.getPosition(),
                rightEncoder.getPosition());
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
        leftFrontMotor.setVoltage(leftVolts * (kLeftReversed ? -1 : 1));
        rightFrontMotor.setVoltage(rightVolts * (kRightReversed ? -1 : 1));
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
     * Resets the odometry to the current pose.
     */
    @Override
    public void resetOdometry() {
        resetEncoders();
        driveOdometry.resetPosition(getPose(), Rotation2d.fromDegrees(getHeading()));
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    @Override
    public void resetOdometry(final Pose2d pose) {
        resetEncoders();
        driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
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
    public Command getRamseteCommand(final Pose2d start, final List<Translation2d> interiorWaypoints,
            final Pose2d end) {

        // Create trajectory to follow
        final Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, end,
                trajectoryConfig);

        // return the RamseteCommand to run
        return new RamseteCommand(trajectory, this::getPose, new RamseteController(kRamseteB, kRamseteZeta),
                new SimpleMotorFeedforward(kS, kV, kA), driveKinematics, this::getVelocity, new PIDController(kP, 0, 0),
                new PIDController(kP, 0, 0), this::tankDriveVolts, this);
    }

    /*
     * Normal Drive Methods
     */

    /**
     * Resets the drive encoders to currently read a position of 0.
     */
    @Override
    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub
    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
    }

    @Override
    public void stop() {
        drive.tankDrive(0, 0);
    }

    /*
     * Drive constraint values
     */

    private static final double speedFactor = 1;
    private static final double turnFactor = 1;
    private static final double speedConstraintFactor = 1;
    private static final double turnConstraintFactor = 1;

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        drive.arcadeDrive(hmiSpeed * speedFactor, hmiTurn * turnFactor);
    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn, boolean constrained) {
        if (constrained) {
            drive.arcadeDrive(hmiSpeed * speedConstraintFactor, hmiTurn * turnConstraintFactor);
        } else {
            drive.arcadeDrive(hmiSpeed * speedFactor, hmiTurn * turnFactor);
        }
    }

    @Override
    public double getLeftEncoderClicks() {
        return leftEncoder.getPosition();
    }

    @Override
    public double getRightEncoderClicks() {
        return rightEncoder.getPosition();
    }

    /**
     * Returns the robot's current heading (180 to -180) in degrees.
     */
    @Override
    public double getHeading() {
        return Math.IEEEremainder(nav.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
    }

    @Override
    public double convertInchesToEncoderClicks(double inches) {
        return inches * (1 / 12) // Conversion to feet
                * 3.281 // Conversion to meters
                * (1 / (2 * Math.PI * kWheelRadius)) // Convert to wheel revolutions (Circumference)
                * (kBeltGearing) // Convert to output shaft revolutions (Belt gearing)
                * (1 / kGearboxGearing); // Convert to motor revolutions (TB Mini gearing)
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
}
