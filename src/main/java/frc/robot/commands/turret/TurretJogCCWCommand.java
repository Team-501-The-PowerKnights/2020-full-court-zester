/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

public class TurretJogCCWCommand extends TurretCommandBase {

  public TurretJogCCWCommand() {
  }

  @Override
  public void execute() {
    super.execute();

    turret.jogCCW();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

}