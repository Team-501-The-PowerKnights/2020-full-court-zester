/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.OI;

import riolog.RioLogger;

/**
 * 
 */
public abstract class PKManualCommand extends PKCommandBase {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(PKManualCommand.class.getName());

    // Handle to the OI object for access to joysticks
    protected final OI oi;

    protected PKManualCommand() {
        logger.info("constructing for {}", getName());

        oi = OI.getInstance();

        logger.info("constructed");
    }

}
