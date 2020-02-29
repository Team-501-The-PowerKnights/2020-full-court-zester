/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.OI;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterEnableSpin extends ShooterCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ShooterEnableSpin.class.getName());

  public ShooterEnableSpin() {
    logger.info("constructing {}", getName());

    logger.info("constructed");
  }

  @Override
  public void execute() {
    super.execute();

    shooter.shoot();
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);

    // Never stop the shooter, as default is idle
  }

  @Override
  public boolean isFinished() {
    // Scheduler will interrupt command when switch is turned off
    return !(OI.getInstance().isShooterRevEnabled());
  }

}