/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.turret.ITurretSubsystem;
import frc.robot.subsystems.turret.TurretFactory;
import riolog.RioLogger;

public class TurretFineAdjustment extends PKCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(TurretFineAdjustment.class.getName());

  private final OI oi;
  private final ITurretSubsystem turret;

  private double firstVal;
  private boolean hasEscaped;

  public TurretFineAdjustment() {
    logger.info("constructing {}", getName());

    oi = OI.getInstance();
    turret = TurretFactory.getInstance();

    logger.info("constructed");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    firstVal = oi.getTurretFineAdjustment();
    hasEscaped = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if ((Math.abs(oi.getTurretFineAdjustment() - firstVal) / firstVal) <= 0.05 && !(hasEscaped)) {
      hasEscaped = true;
    }

    if (hasEscaped) {
      turret.fineTuneTargetAngle(oi.getTurretFineAdjustment());
    }
  }

}
