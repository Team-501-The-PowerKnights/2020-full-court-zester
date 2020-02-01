/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

class ShooterSubsystem extends BaseShooterSubsystem {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ShooterSubsystem.class.getName());

  /**
   * Shooter constant values
   */

  private static final double VPGearing = 1;
  private static final double beltGearing = 1;
  private static final double countsPerRevolution = 1;
  private static final double flywheelP = 0;
  private static final double flywheelI = 0;
  private static final double flywheelD = 0;
  private static final double flywheelF = 0;

  private static final double turretMaxAngle = 270;
  private static final double turretMinAngle = 0;
  private static final double turretP = 0;
  private static final double turretI = 0;
  private static final double turretD = 0;
  private static final double turretF = 0;

  /**
   * Mechanisms and sensors
   */

  private CANSparkMax motor;
  private CANSparkMax shootSlave0;
  private CANPIDController shootPID;

  private CANSparkMax turret;
  private CANEncoder turretEncoder;
  private CANPIDController turretPID;

  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {
    logger.info("constructing");

    motor = new CANSparkMax(0, MotorType.kBrushless);
    shootSlave0 = new CANSparkMax(0, MotorType.kBrushless);

    shootSlave0.follow(motor);

    shootPID = new CANPIDController(motor);
    shootPID.setP(flywheelP);
    shootPID.setI(flywheelI);
    shootPID.setD(flywheelD);
    shootPID.setFF(flywheelF);

    turret = new CANSparkMax(0, MotorType.kBrushless);
    turretEncoder = new CANEncoder(turret);
    turretPID = new CANPIDController(turret);
    turretPID.setP(turretP);
    turretPID.setI(turretI);
    turretPID.setD(turretD);
    turretPID.setFF(turretF);

    logger.info("constructed");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void updateTelemetry() {
    SmartDashboard.putNumber(TelemetryNames.Shooter.angle, convertTurretCountsToAngle(turretEncoder.getPosition()));
  }

  @Override
  public void stop() {
    shootPID.setReference(0, ControlType.kVelocity);
    motor.set(0.0);
  }

  @Override
  public void shoot(double dist) {
    // TODO - Trajectory generation for speed
    shootPID.setReference(0.2 /* generated speed */, ControlType.kVelocity);
  }

  @Override
  public void shoot() {
    // TODO - Trajectory generation from vision data
    shootPID.setReference(0.2 /* generated speed */, ControlType.kVelocity);
  }

  @Override
  public void setTurretAngle(double angle) {
    if (angle >= turretMaxAngle) {
      angle = turretMaxAngle;
    } else if (angle <= turretMinAngle) {
      angle = turretMinAngle;
    }

    double targetCounts = convertTurretAngleToCounts(angle);

    turretPID.setReference(targetCounts, ControlType.kPosition);
  }

  @Override
  public void home() {
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

  private double convertTurretAngleToCounts(double angle) {
    double counts = angle * (1 / 360 /* 360 degrees per 1 revolution */) / VPGearing / beltGearing
        * countsPerRevolution;

    return counts;
  }

  private double convertTurretCountsToAngle(double counts) {
    double angle = (counts / countsPerRevolution) * VPGearing * beltGearing * 360 /* 360 degrees per 1 revolution */;

    return angle;
  }
}
