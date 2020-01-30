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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;

public class SuitcaseShooterSubsystem implements IShooterSubsystem {
  private static final String myName = TelemetryNames.Shooter.name;

  private static IShooterSubsystem ourInstance;

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
   * Shooter constant values
   */
  private static final double flywheelP = 0;
  private static final double flywheelI = 0;
  private static final double flywheelD = 0;
  private static final double flywheelF = 0;

  /**
   * Mechanisms and sensors
   */

  private CANSparkMax shootMaster;
  private CANPIDController shootPID;

  /**
   * Creates a new ShooterSubsystem.
   */
  public SuitcaseShooterSubsystem() {
    shootMaster = new CANSparkMax(50, MotorType.kBrushless);

    shootPID = new CANPIDController(shootMaster);
    shootPID.setP(flywheelP);
    shootPID.setI(flywheelI);
    shootPID.setD(flywheelD);
    shootPID.setFF(flywheelF);
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
    shootPID.setReference(targetRPM, ControlType.kVelocity); // Set target velocity
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
    // TODO Auto-generated method stub

  }
}
