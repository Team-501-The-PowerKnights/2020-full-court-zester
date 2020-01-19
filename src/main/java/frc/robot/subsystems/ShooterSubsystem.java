/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

public class ShooterSubsystem extends SubsystemBase implements ITelemetryProvider {

  private static final String myName = TelemetryNames.Shooter.name;

  private static ShooterSubsystem ourInstance;

  public static synchronized void constructInstance() {
    SmartDashboard.putBoolean(TelemetryNames.Shooter.status, false);

    if (ourInstance != null) {
      throw new IllegalStateException(myName + " already constructed");
    }

    ourInstance = new ShooterSubsystem();

    SmartDashboard.putBoolean(TelemetryNames.Shooter.status, true);
  }

  public static ShooterSubsystem getInstance() {
    
    if (ourInstance == null) {
      throw new IllegalStateException(myName + " not constructed yet");
    }

    return ourInstance;
  }

  private CANSparkMax shootMaster;
  private CANSparkMax shootSlave0;
  // private CANSparkMax shootSlave1;
  // private CANSparkMax shootSlave2;
  private CANPIDController shootPID;

  private CANSparkMax turret;
  private CANEncoder turretEncoder;
  private CANPIDController turretPID;

  // Constants
  private static final double VPGearing = ShooterConstants.Flywheel.kFlywheelVPGearing;
  private static final double beltGearing = ShooterConstants.Flywheel.kFlywheelBeltGearing;
  private static final double countsPerRevolution = ShooterConstants.Flywheel.kCountsPerRevolution;

  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {
    shootMaster = new CANSparkMax(0, MotorType.kBrushless); // TODO - Determine actual motor ports
    shootSlave0 = new CANSparkMax(0, MotorType.kBrushless);
    // shootSlave1 = new CANSparkMax(0, MotorType.kBrushless);
    // shootSlave2 = new CANSparkMax(0, MotorType.kBrushless);

    shootSlave0.follow(shootMaster);
    // shootSlave1.follow(shootMaster);
    // shootSlave2.follow(shootMaster);

    shootPID = new CANPIDController(shootMaster);
    shootPID.setP(ShooterConstants.Flywheel.kP);
    shootPID.setI(ShooterConstants.Flywheel.kI);
    shootPID.setD(ShooterConstants.Flywheel.kD);
    shootPID.setFF(ShooterConstants.Flywheel.kF);

    turret = new CANSparkMax(0, MotorType.kBrushless);
    turretEncoder = new CANEncoder(turret);
    turretPID = new CANPIDController(turret);
    turretPID.setP(ShooterConstants.Turret.kP);
    turretPID.setI(ShooterConstants.Turret.kI);
    turretPID.setD(ShooterConstants.Turret.kD);
    turretPID.setFF(ShooterConstants.Turret.kF);
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

/**
 * Sets the turret to a targetAngle (position).
 * 
 * @param targetAngle
 */
  public void setTurretAngle(double targetAngle) {

    if (targetAngle >= ShooterConstants.Turret.kMaxAngle) {
      targetAngle = ShooterConstants.Turret.kMaxAngle;
    } else if (targetAngle <= ShooterConstants.Turret.kMinAngle) {
      targetAngle = ShooterConstants.Turret.kMinAngle;
    }

    double targetCounts = convertTurretAngleToCounts(targetAngle);

    turretPID.setReference(targetCounts, ControlType.kPosition); // Tell the motor the encoder counts necessary to reach the specified angle
  }

  /**
   * Gets the current angle of the turret.
   */
  public void getTurretAngle() {

  }

  private double convertTurretAngleToCounts(double angle) {
    double counts = angle * (1/360 /* 360 degrees per 1 revolution */) / VPGearing / beltGearing * countsPerRevolution;

    return counts;
  }

  private double convertTurretCountsToAngle(double counts) {
    double angle = (counts / countsPerRevolution) * VPGearing * beltGearing * 360 /* 360 degrees per 1 revolution */;
    
    return angle;
  }

  @Override
  public void updateTelemetry() {
    SmartDashboard.putNumber(TelemetryNames.Shooter.angle, convertTurretCountsToAngle(turretEncoder.getPosition()));
  }
}
