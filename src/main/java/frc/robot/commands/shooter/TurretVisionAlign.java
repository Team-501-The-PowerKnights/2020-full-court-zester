/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

public class TurretVisionAlign extends ShooterCommandBase {

  public TurretVisionAlign() {

  }

  @Override
  public void execute() {
    super.execute();

    shooter.setLED(3);

    shooter.setCamMode(true);

    shooter.setAngleFromVision();
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);

    shooter.setLED(1);

    shooter.setCamMode(false);
  }

}
