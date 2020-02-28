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
 * - Drives "long" distance
 * @formatter:on
 */
public class AutoFullTrench extends PKParallelCommandGroup {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final Logger logger = RioLogger.getLogger(AutoFullTrench.class.getName());

    /**
     * Creates a new AutoSequence.
     */
    public AutoFullTrench() {
        super(new PKParallelCommandGroup(new AutoFirePosePreset(),
                new PKSequentialCommandGroup(new AutoTurretPositionBack(), new TurretVisionAlign())), new IngestPose(),
                new DriveForwardDistance(16.2) // Confirm distance
        );

        // super(new PKParallelCommandGroup(new FirePoseFormula(),
        // new PKSequentialCommandGroup(new AutoTurretPositionBack(), new
        // TurretVisionAlign())), new IngestPose(),
        // new DriveForwardDistance(10.2)
        // );
    }
}
