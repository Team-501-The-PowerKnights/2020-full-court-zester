/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.IDriveSubsystem;

public class DriveBaseCommand extends CommandBase {

  private IDriveSubsystem drive;
  private double speed;
  private double turn;

  /**
   * Creates a new DriveBaseCommand.
   */
  public DriveBaseCommand(IDriveSubsystem drive, double speed, double turn) {
    this.drive = drive;
    this.speed = speed;
    this.turn = turn;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.arcadeDrive(speed, turn);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
