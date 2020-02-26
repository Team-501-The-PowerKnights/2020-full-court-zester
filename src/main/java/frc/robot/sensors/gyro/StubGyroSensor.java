/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Provides implementation of <code>IGyroSensor</code> which has no sensor or
 * other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
class StubGyroSensor extends BaseGyroSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubGyroSensor.class.getName());

    StubGyroSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public double getRoll() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getPitch() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getYaw() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getAngle() {
        // Stub doesn't implement this
        return 0;
    }

}
