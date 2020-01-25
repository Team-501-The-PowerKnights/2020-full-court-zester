/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.Constants.DriveConstants;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

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

  private CANSparkMax leftFrontMotor;
  private CANSparkMax leftRearMotor;
  private CANSparkMax rightFrontMotor;
  private CANSparkMax rightRearMotor;

  private SpeedControllerGroup left;
  private SpeedControllerGroup right;

  private CANEncoder leftEncoder;
  private CANEncoder rightEncoder;

  private DifferentialDrive drive;
  public DifferentialDriveKinematics driveKinematics;
  public DifferentialDriveOdometry driveOdometry;

  private ADIS16448_IMU nav;

  public DriveSubsystem() {
    leftFrontMotor = new CANSparkMax(RobotMap.kLeftFrontDrivePort, MotorType.kBrushless);
    leftRearMotor = new CANSparkMax(RobotMap.kLeftRearDrivePort, MotorType.kBrushless);
    rightFrontMotor = new CANSparkMax(RobotMap.kRightFrontDrivePort, MotorType.kBrushless);
    rightRearMotor = new CANSparkMax(RobotMap.kRightRearDrivePort, MotorType.kBrushless);

    left = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
    right = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);

    left.setInverted(false);
    right.setInverted(true);

    leftEncoder = new CANEncoder(leftFrontMotor);
    rightEncoder = new CANEncoder(rightFrontMotor);

    nav = new ADIS16448_IMU();

    drive = new DifferentialDrive(left, right);
    driveKinematics = new DifferentialDriveKinematics(DriveConstants.trackWidth);
    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(nav.getAngle()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    driveOdometry.update(Rotation2d.fromDegrees(nav.getAngle()), leftEncoder.getPosition(), rightEncoder.getPosition());
  }

  /**
   * Sends y-axis speed and z-axis rotation to the DifferentialDrive arcadeDrive
   * function.
   * 
   * @param speed
   * @param turn
   */
  public void arcadeDrive(double speed, double turn) {
    drive.arcadeDrive(speed, turn);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftFrontMotor.setVoltage(leftVolts * (Constants.DriveConstants.kLeftReversed ? -1 : 1));
    rightFrontMotor.setVoltage(rightVolts * (Constants.DriveConstants.kRightReversed ? -1 : 1));
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getVelocity() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public CANEncoder getLeftEncoder() {
    return leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public CANEncoder getRightEncoder() {
    return rightEncoder;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more
   * slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    nav.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from 180 to 180
   */
  public double getHeading() {
    return Math.IEEEremainder(nav.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return nav.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  @Override
  public void updateTelemetry() {
    // TODO Auto-generated method stub

  }
}
