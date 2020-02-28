/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import org.slf4j.Logger;

import riolog.RioLogger;

public class ClimberExtend extends ClimberCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ClimberExtend.class.getName());

  public ClimberExtend() {
    logger.info("constructing {}", getName());

    logger.info("constructed");
  }

  // Called repeatedly after execute to determine if command is finished
  @Override
  public void execute() {
    super.execute();

    climber.extend();
  }

  // Called once when either the Command finishes normally, or when it
  // is interrupted/canceled.
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);

    climber.stop();
  }

}
