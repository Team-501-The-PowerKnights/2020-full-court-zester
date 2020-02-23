/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DoNothingButton extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DoNothingButton.class.getName());

    // Something for unique identification
    @SuppressWarnings("unused")
    private final String button;

    public DoNothingButton(String button) {
        logger.info("constructing {}", getName());

        this.button = button;

        logger.info("Stub/do nothing button {}", button);
    }

}
