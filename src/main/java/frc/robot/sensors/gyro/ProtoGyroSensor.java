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
 * Provides implementation of <code>IGyroSensor</code> for the
 * <i>Suitcase-Bot</i> which is based on the navX-MXP sensor.
 */
class ProtoGyroSensor extends SuitcaseGyroSensor {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(ProtoGyroSensor.class.getName());

    ProtoGyroSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
