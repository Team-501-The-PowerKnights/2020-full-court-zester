/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules.rpi;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
class StubRPIModule extends BaseRPIModule {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubRPIModule.class.getName());

    public StubRPIModule() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // No stub implementation
    }

    @Override
    public void updatePreferences() {
        // No stub implementation
    }

    @Override
    public void disable() {
        // No stub implementation
    }

}
