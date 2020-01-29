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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

  /**
   * Shooter constant values
   */

  private static final double flywheelVPGearing = 1;
  private static final double flywheelBeltGearing = 1;
  private static final double flywheelCountsPerRevolution = 1;
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

  private CANSparkMax shootMaster;
  private CANSparkMax shootSlave0;
  // private CANSparkMax shootSlave1;
  // private CANSparkMax shootSlave2;
  private CANPIDController shootPID;

  private CANSparkMax turret;
  private CANEncoder turretEncoder;
  private CANPIDController turretPID;

  // Constants
  private static final double VPGearing = flywheelVPGearing;
  private static final double beltGearing = flywheelBeltGearing;
  private static final double countsPerRevolution = flywheelCountsPerRevolution;

  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {
    shootMaster = new CANSparkMax(0, MotorType.kBrushless);
    shootSlave0 = new CANSparkMax(0, MotorType.kBrushless);
    // shootSlave1 = new CANSparkMax(RobotMap.kShooterSlave1Port,
    // MotorType.kBrushless);
    // shootSlave2 = new CANSparkMax(RobotMap.kShooterSlave2Port,
    // MotorType.kBrushless);

    shootSlave0.follow(shootMaster);
    // shootSlave1.follow(shootMaster);
    // shootSlave2.follow(shootMaster);

    shootPID = new CANPIDController(shootMaster);
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

    if (targetAngle >= turretMaxAngle) {
      targetAngle = turretMaxAngle;
    } else if (targetAngle <= turretMinAngle) {
      targetAngle = turretMinAngle;
    }

    double targetCounts = convertTurretAngleToCounts(targetAngle);

    turretPID.setReference(targetCounts, ControlType.kPosition); // Tell the motor the encoder counts necessary to reach
                                                                 // the specified angle
  }

  /**
   * Gets the current angle of the turret.
   */
  public void getTurretAngle() {

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

  @Override
  public void updateTelemetry() {
    SmartDashboard.putNumber(TelemetryNames.Shooter.angle, convertTurretCountsToAngle(turretEncoder.getPosition()));
  }
}
