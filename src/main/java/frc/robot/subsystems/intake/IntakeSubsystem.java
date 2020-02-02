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

import riolog.RioLogger;

public class IntakeSubsystem extends BaseIntakeSubsystem {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(IntakeSubsystem.class.getName());

  private TalonSRX motor;

  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {
    logger.info("constructing");

    motor = new TalonSRX(0);

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
    motor.set(ControlMode.PercentOutput, 0.0);
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
}