/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hopper;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.hopper.HopperFactory;
import frc.robot.subsystems.hopper.IHopperSubsystem;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class HopperCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(HopperCommandBase.class.getName());

    // Handle to our subsystem
    protected IHopperSubsystem hopper;

    public HopperCommandBase() {
        logger.info("constructing {}", getName());

        hopper = HopperFactory.getInstance();

        addRequirements(hopper);

        logger.info("constructed");
    }

}
