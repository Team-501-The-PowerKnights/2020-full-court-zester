/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterIdle extends ShooterCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ShooterIdle.class.getName());

  public ShooterIdle() {
    logger.info("constructing {}", getName());

    logger.info("constructed");
  }

  @Override
  public void execute() {
    super.execute();

    shooter.setSpeed(29, 0.20);
  }

}