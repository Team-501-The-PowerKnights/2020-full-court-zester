/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

class ProtoShooterSubsystem extends BaseShooterSubsystem {

  /**
   * Shooter constant values
   */
  private static final double flywheelP = 0;
  private static final double flywheelI = 0;
  private static final double flywheelD = 0;
  private static final double flywheelF = 0;

  /**
   * Mechanisms and sensors
   */

  private CANSparkMax motor;
  private CANPIDController shootPID;

  /**
   * Creates a new ShooterSubsystem.
   */
  public ProtoShooterSubsystem() {
    motor = new CANSparkMax(50, MotorType.kBrushless);

    shootPID = new CANPIDController(motor);
    shootPID.setP(flywheelP);
    shootPID.setI(flywheelI);
    shootPID.setD(flywheelD);
    shootPID.setFF(flywheelF);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
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
  public void updateTelemetry() {
    // TODO Auto-generated method stub

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
  }

  @Override
  public void home() {
  }
}
