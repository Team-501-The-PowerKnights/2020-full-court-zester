/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import frc.robot.commands.PKCommand;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DriveSwap extends PKCommand {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(DriveForwardTimed.class.getName());

  // Handle to our subsystem
  private IDriveSubsystem drive;
  
  // FIXME - Is dependency right?
  // FIXME - Should pause for a couple of cycles of execute to give time to coast out

  /**
   * Creates a new DriveJoystickControl.
   */
  public DriveSwap() {
    logger.info("constructing {}", getName());

    drive = DriveFactory.getInstance();
    addRequirements(drive);

    logger.info("constructed");
  }

  // Called once just before this Command runs the first time
  @Override
  public void initialize() {
    drive.swap();
  }

}
