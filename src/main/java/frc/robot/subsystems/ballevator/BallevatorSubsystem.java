/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.ballevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import riolog.RioLogger;

class BallevatorSubsystem extends BaseBallevatorSubsystem {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(BallevatorSubsystem.class.getName());

  private TalonSRX motor;

  public BallevatorSubsystem() {
    logger.info("constructing");

    motor = new TalonSRX(51);

    logger.info("constructed");
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
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void lift() {
    motor.set(ControlMode.PercentOutput, 0.2); // TODO - determine actual predefined value to run ballevator at
  }

  @Override
  public void lower() {
    motor.set(ControlMode.PercentOutput, -0.2); // TODO - determine actual predefined value to run ballevator at
  }

}
