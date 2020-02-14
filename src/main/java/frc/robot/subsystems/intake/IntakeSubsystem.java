/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import riolog.RioLogger;

public class IntakeSubsystem extends BaseIntakeSubsystem {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(IntakeSubsystem.class.getName());

  private TalonSRX ingest;
  private TalonSRX motor;

  private PIDController pid;
  private double pidP;
  private double pidI;
  private double pidD;

  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {
    logger.info("constructing");

    ingest = new TalonSRX(41);
    motor = new TalonSRX(42);

    pid = new PIDController(0.0, 0.0, 0.0);
    pidP = 0.0;
    pidI = 0.0;
    pidD = 0.0;
    pid.setP(pidP);
    pid.setI(pidI);
    pid.setD(pidD);

    logger.info("constructed");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void updateTelemetry() {
  }

  @Override
  public void stop() {
    ingest.set(ControlMode.PercentOutput, 0.0);
    motor.set(ControlMode.PercentOutput, 0.0);

    
  }

  @Override
  public void validateCalibration() {
    // TODO Auto-generated method stub

  }

  @Override
  public void updatePreferences() {
    // TODO Get PID values from preferences

  }

  @Override
  public void disable() {
    // TODO Auto-generated method stub

  }

  @Override
  public void pullIn() {
    motor.set(ControlMode.PercentOutput, 0.2); // TODO - determine actual preset speed values
  }

  @Override
  public void pullIn(double speed) {
    motor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void pushOut() {
    motor.set(ControlMode.PercentOutput, -0.2); // TODO - determine actual preset speed values
  }

  @Override
  public void pushOut(double speed) {
    motor.set(ControlMode.PercentOutput, -speed);
  }

  @Override
  public void raise() {
    motor.set(ControlMode.Position, 0.0); // TODO - determine actual position
  }

  @Override
  public void lower() {
    motor.set(ControlMode.Position, 25.0); // TODO - determine actual position
  }
}