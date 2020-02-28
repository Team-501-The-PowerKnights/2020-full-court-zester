/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import org.slf4j.Logger;

import riolog.RioLogger;

class StubClimberSubsystem extends BaseClimberSubsystem {

  private static final Logger logger = RioLogger.getLogger(StubClimberSubsystem.class.getName());

  public StubClimberSubsystem() {
    logger.info("constructing");

    logger.info("constructed");
  }

  @Override
  public void periodic() {
    // Stub doesn't implement this
  }

  @Override
  public void updateTelemetry() {
    // Stub doesn't implement this
  }

  @Override
  public void validateCalibration() {
    // Stub doesn't implement this
  }

  @Override
  public void updatePreferences() {
    // Stub doesn't implement this
  }

  @Override
  public void disable() {
    // Stub doesn't implement this
  }

  @Override
  public void stop() {
    // Stub doesn't implement this
  }

  @Override
  public void extend() {
    // Stub doesn't implement this
  }

  @Override
  public void climb() {
    // Stub doesn't implement this
  }

  @Override
  public void retract() {
    // Stub doesn't implement this
  }

}
