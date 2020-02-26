/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.limelight;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Provides implementation of <code>ILimelightSensor</code> which has no sensor
 * or other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
class StubLimelightSensor extends BaseLimelightSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubLimelightSensor.class.getName());

    StubLimelightSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getError() {
        // Stub doesn't do anything useful
        return 0;
    }

}
