/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheel;

import org.slf4j.Logger;

import riolog.RioLogger;

public class WheelRunClockwise extends WheelCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(WheelRunClockwise.class.getName());

  /**
   * Creates a new WheelRunCounterClockwise.
   */
  public WheelRunClockwise() {
    logger.info("constructing {}", getName());

    logger.info("constructed");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    super.execute();

    wheel.runClockwise();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);

    wheel.stop();
  }

}
