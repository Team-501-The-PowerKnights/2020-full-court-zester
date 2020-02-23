/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheel;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.wheel.IWheelSubsystem;
import frc.robot.subsystems.wheel.WheelFactory;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class WheelCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelCommandBase.class.getName());

    // Handle to our subsystem
    protected IWheelSubsystem wheel;

    public WheelCommandBase() {
        logger.info("constructing {}", getName());

        wheel = WheelFactory.getInstance();

        addRequirements(wheel);

        logger.info("constructed");
    }

}
