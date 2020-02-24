/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballevator;

public class BallevatorLower extends BallevatorCommandBase {

  public BallevatorLower() {
  }

  @Override
  public void execute() {
    super.execute();

    ballevator.lower();
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);

    ballevator.stop();
  }

}
