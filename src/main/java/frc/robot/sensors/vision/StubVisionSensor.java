/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Provides implementation of <code>IVisionSensor</code> which has no sensor or
 * other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
class StubVisionSensor extends BaseVisionSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubVisionSensor.class.getName());

    StubVisionSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // Stub doesn't implement anything useful
    }

    @Override
    public double getError() {
        // Stub doesn't do anything useful
        return 0;
    }

    @Override
    public double getY() {
        // Stub doesn't do anything useful
        return 0;
    }

    @Override
    public boolean isLocked() {
        // Stub doesn't do anything useful
        return false;
    }

}
