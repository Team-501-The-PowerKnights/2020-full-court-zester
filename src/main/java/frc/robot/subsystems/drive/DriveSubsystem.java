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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public class DriveSubsystem extends SubsystemBase implements ITelemetryProvider {

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
    private static final double s = 0.0645; // Volts
    private static final double v = 2.84; // VoltSeconds Per Meter
    private static final double a = 0.28; // VoltSecondsSquared Per Meter
    private static final double p = 2.53; // Drive Velocity
    private static final double trackWidth = 0.616; // Meters
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;
    private static final double maxSpeed = 3.04; // Meters Per Second
    private static final double maxAcceleration = 0.5; // Meters Per Second Squared
    private static final boolean gyroReversed = true;
    private static final boolean leftReversed = false;
    private static final boolean rightReversed = false;

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

        autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(s, v, a),
                driveKinematics, 10);

        trajectoryConfig = new TrajectoryConfig(maxSpeed, maxAcceleration).setKinematics(driveKinematics)
                .addConstraint(autoVoltageConstraint);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        driveOdometry.update(Rotation2d.fromDegrees(nav.getAngle()), leftEncoder.getPosition(),
                rightEncoder.getPosition());
    }

    /**
     * Sends y-axis speed and z-axis rotation to the DifferentialDrive arcadeDrive
     * function.
     * 
     * @param speed
     * @param turn
     */
    public void arcadeDrive(final double speed, final double turn) {
        drive.arcadeDrive(speed, turn);
    }

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

    public Command getRamseteCommand(final Pose2d start, final List<Translation2d> interiorWaypoints,
            final Pose2d end) {

        // Create trajectory to follow
        final Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, end,
                trajectoryConfig);

        // return the RamseteCommand to run
        return new RamseteCommand(trajectory, this::getPose, new RamseteController(ramseteB, ramseteZeta),
                new SimpleMotorFeedforward(s, v, a), driveKinematics, this::getVelocity, new PIDController(p, 0, 0),
                new PIDController(p, 0, 0), this::tankDriveVolts, this);
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }
}
