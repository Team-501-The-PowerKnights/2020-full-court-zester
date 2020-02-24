/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

public class TurretRightCommand extends ShooterCommandBase {

  public TurretRightCommand() {
  }

  @Override
  public void execute() {
    super.execute();

    shooter.setTurretAngle(-90);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

}
