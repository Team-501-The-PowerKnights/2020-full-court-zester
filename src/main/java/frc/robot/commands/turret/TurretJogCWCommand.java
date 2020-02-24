/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

public class TurretJogCWCommand extends TurretCommandBase {

  public TurretJogCWCommand() {
  }

  @Override
  public void execute() {
    super.execute();

    turret.jogCW();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

}
