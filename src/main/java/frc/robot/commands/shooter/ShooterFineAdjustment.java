/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;
import riolog.RioLogger;

public class ShooterFineAdjustment extends PKCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ShooterFineAdjustment.class.getName());

  private final OI oi;
  private final IShooterSubsystem shooter;

  private double firstVal, lastVal;
  private boolean passedInitialDeadband;

  public ShooterFineAdjustment() {
    logger.info("constructing {}", getName());

    oi = OI.getInstance();
    shooter = ShooterFactory.getInstance();

    logger.info("constructed");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    firstVal = oi.getShooterFineAdjustment();
    lastVal = firstVal;
    passedInitialDeadband = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentVal = oi.getShooterFineAdjustment();

    if (!(passedInitialDeadband) && (Math.abs(currentVal - firstVal) / firstVal) <= 0.05) {
      passedInitialDeadband = true;
    }

    if (passedInitialDeadband && (lastVal != currentVal)) {
      shooter.fineTuneTargetRpm(currentVal);
    }

    lastVal = currentVal;
  }

}
