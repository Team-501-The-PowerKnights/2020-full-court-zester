/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.Constants.DriveConstants;
import frc.robot.commands.DoNothing;
import frc.robot.commands.DriveBaseCommand;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;

import riolog.RioLogger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(RobotContainer.class.getName());

    // The robot's subsystems and commands are defined here...
    private final IDriveSubsystem drive = DriveFactory.getInstance();

    private final Joystick driverStick;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        driverStick = new Joystick(0);
        // Configure the button bindings
        configureButtonBindings();

        drive.setDefaultCommand(new DriveBaseCommand(drive, driverStick.getRawAxis(Constants.OIConstants.speedAxis),
                driverStick.getRawAxis(Constants.OIConstants.turnAxis)));
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return new DoNothing();

        // TODO - Implement this with new way

        // // Create voltage constraint for trajectory following
        // DifferentialDriveVoltageConstraint autoVoltageConstraint = new
        // DifferentialDriveVoltageConstraint(
        // new SimpleMotorFeedforward(DriveConstants.kS, DriveConstants.kV,
        // DriveConstants.kA),
        // driveSubsystem.driveKinematics, 10);

        // TrajectoryConfig trajectoryConfig = new
        // TrajectoryConfig(DriveConstants.kMaxSpeed, DriveConstants.kMaxAcceleration)
        // .setKinematics(driveSubsystem.driveKinematics).addConstraint(autoVoltageConstraint);

        // Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // // Start at the origin facing the +X direction
        // new Pose2d(0, 0, new Rotation2d(0)),
        // // Pass through these two interior waypoints, making an 's' curve path
        // List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        // // End 3 meters straight ahead of where we started, facing forward
        // new Pose2d(3, 0, new Rotation2d(0)),
        // // Pass config
        // trajectoryConfig);
        // // An ExampleCommand will run in autonomous
        // return new RamseteCommand(exampleTrajectory, driveSubsystem::getPose,
        // new RamseteController(DriveConstants.kRamseteB, DriveConstants.kRamseteZeta),
        // new SimpleMotorFeedforward(DriveConstants.kS, DriveConstants.kV,
        // DriveConstants.kA),
        // driveSubsystem.driveKinematics, driveSubsystem::getVelocity, new
        // PIDController(DriveConstants.kP, 0, 0),
        // new PIDController(DriveConstants.kP, 0, 0), driveSubsystem::tankDriveVolts,
        // driveSubsystem);
    }
}
