/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.commands.drive.DriveForwardDistance;
import frc.robot.commands.turret.AutoTurretPositionBack;
import frc.robot.commands.turret.TurretVisionAlign;
import riolog.RioLogger;

/**
 * Standard Auto w/ Everything
 * @formatter:off
 * - Drives "short" distance (not trench)
 * @formatter:on
 */
public class AutoFullShort extends PKParallelCommandGroup {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final Logger logger = RioLogger.getLogger(AutoFullShort.class.getName());

    public AutoFullShort() {
        super(new PKParallelCommandGroup(new AutoFirePosePreset(),
                new PKSequentialCommandGroup(new AutoTurretPositionBack(), new TurretVisionAlign())), new IngestPose(),
                new DriveForwardDistance(8.0) // Confirm distance
        );
    }

}
