/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.turret.ITurretSubsystem;
import frc.robot.subsystems.turret.TurretFactory;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class TurretCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(TurretCommandBase.class.getName());

    // Handle to our subsystem
    protected ITurretSubsystem turret;

    public TurretCommandBase() {
        logger.info("constructing {}", getName());

        turret = TurretFactory.getInstance();

        addRequirements(turret);

        logger.info("constructed");
    }

}
