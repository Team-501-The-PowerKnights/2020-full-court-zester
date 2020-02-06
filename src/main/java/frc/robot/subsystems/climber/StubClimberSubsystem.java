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

  /**
   * Creates a new ClimberSubsystem.
   */
  public StubClimberSubsystem() {
    logger.info("constructing");

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

  }

  @Override
  public void validateCalibration() {

  }

  @Override
  public void updatePreferences() {

  }

  @Override
  public void disable() {

  }
}
