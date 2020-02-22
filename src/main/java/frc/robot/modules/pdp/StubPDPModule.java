/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules.pdp;

import org.slf4j.Logger;

import riolog.RioLogger;

class StubPDPModule extends BasePDPModule {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubPDPModule.class.getName());

    public StubPDPModule() {
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

    @Override
    public double getBusVoltage() {
        // No stub implementation
        return 0;
    }

    @Override
    public double getTotalCurrent() {
        // No stub implementation
        return 0;
    }

    @Override
    public double getTotalEnergy() {
        // No stub implementation
        return 0;
    }

    @Override
    public double getCurrent(int deviceID) {
        // No stub implementation
        return 0;
    }

}
