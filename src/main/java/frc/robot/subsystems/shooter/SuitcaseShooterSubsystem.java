/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

public class SuitcaseShooterSubsystem extends BaseShooterSubsystem {

  public static synchronized void constructInstance() {
    SmartDashboard.putBoolean(TelemetryNames.Shooter.status, false);

    if (ourInstance != null) {
      throw new IllegalStateException(myName + " already constructed");
    }

    ourInstance = new SuitcaseShooterSubsystem();

    SmartDashboard.putBoolean(TelemetryNames.Shooter.status, true);
  }

  public static IShooterSubsystem getInstance() {

    if (ourInstance == null) {
      throw new IllegalStateException(myName + " not constructed yet");
    }

    return ourInstance;
  }

  /**
   * Creates a new ShooterSubsystem.
   */
  public SuitcaseShooterSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Sets the target RPM on the output shaft for the shooter flywheel mechanism.
   * 
   * @param targetRPM
   */
  public void setFlywheel(double targetRPM) {
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
  }

  @Override
  public void shoot(double dist) {
  }

  @Override
  public void shoot() {
  }

  @Override
  public void setTurretAngle(double angle) {
  }

  @Override
  public void home() {
  }
}