/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.climber.IClimberSubsystem;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class ClimberCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ClimberCommandBase.class.getName());

    // Handle to our subsystem
    protected IClimberSubsystem climber;

    public ClimberCommandBase() {
        logger.info("constructing {}", getName());

        climber = ClimberFactory.getInstance();

        addRequirements(climber);

        logger.info("constructed");
    }

}
