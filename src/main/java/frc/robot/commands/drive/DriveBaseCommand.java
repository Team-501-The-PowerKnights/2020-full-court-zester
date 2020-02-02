/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommand;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;

import riolog.RioLogger;

public class DriveBaseCommand extends PKCommand {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(DriveBaseCommand.class.getName());

  private IDriveSubsystem drive;
  private OI oi;
  private double speed;
  private double turn;

  /**
   * Creates a new DriveBaseCommand.
   */
  public DriveBaseCommand() {
    logger.info("constructing {}", getName());

    drive = DriveFactory.getInstance();
    oi = OI.getInstance();

    addRequirements(drive);

    // FIXME - Kind of hokey; but avoids code sprawl
    // drive.setDefaultCommand(this);

    logger.info("constructed");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    super.execute();

    speed = oi.getDriverLeftYAxis();
    turn = oi.getDriverRightXAxis();

    drive.drive(speed, turn);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
