/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class DriveCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveCommandBase.class.getName());

    // Handle to our subsystem
    protected IDriveSubsystem drive;

    public DriveCommandBase() {
        logger.info("constructing {}", getName());

        drive = DriveFactory.getInstance();

        addRequirements(drive);

        logger.info("constructed");
    }

}
