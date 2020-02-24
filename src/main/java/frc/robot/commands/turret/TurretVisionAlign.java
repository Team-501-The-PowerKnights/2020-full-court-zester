/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import riolog.RioLogger;

public class TurretVisionAlign extends TurretCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(TurretVisionAlign.class.getName());

  public TurretVisionAlign() {
    logger.info("constructing {}", getName());

    logger.info("constructed");
  }

  @Override
  public void execute() {
    super.execute();

    turret.enableLimelight();

    turret.setAngleFromVision();
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);

    turret.disableLimelight();
  }

}
