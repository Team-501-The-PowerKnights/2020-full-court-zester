/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules.rpi;

import org.slf4j.Logger;

import frc.robot.modules.ModuleNames;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class BaseRPIModule implements IRPIModule {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseRPIModule.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = ModuleNames.rpiName;

    BaseRPIModule() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // Default is to do nothing
    }

    @Override
    public void updatePreferences() {
        // Default is to do nothing
    }

    @Override
    public void disable() {
        // Default is to do nothing
    }

}
