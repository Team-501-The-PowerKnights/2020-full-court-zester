/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.turretlocation;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Provides implementation of <code>ITurretHomeSensor</code> which has no sensor
 * or other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
class StubTurretLocationSensor extends BaseTurretLocationSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubTurretLocationSensor.class.getName());

    StubTurretLocationSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public boolean get() {
        // Stub doesn't implement this - returns false
        return false;
    }

}
