/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKCommand;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterHome extends PKCommand {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ShooterManual.class.getName());

  // Handle to our subsystem
  private IShooterSubsystem shooter;

  public ShooterHome() {
    logger.info("constructing {}", getName());

    shooter = ShooterFactory.getInstance();
    addRequirements(shooter);

    logger.info("constructed");
  }

  // Called once just before this Command runs the first time
  @Override
  public void initialize() {
    shooter.home();
  }

}
