/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.commands.drive.DriveForwardDistance;
import frc.robot.commands.turret.TurretPositionBack;
import frc.robot.commands.turret.TurretVisionAlign;

public class AutoFull extends PKParallelCommandGroup {
  /**
   * Creates a new AutoSequence.
   */
  public AutoFull() {
    super(
        new PKParallelCommandGroup(
          new PKSequentialCommandGroup(
            new TurretPositionBack(),
            new TurretVisionAlign(),
            new FirePoseFormula()
          )
        ),
        new IngestPose(),
        new DriveForwardDistance(15) // Confirm distance
      );
  }
}
