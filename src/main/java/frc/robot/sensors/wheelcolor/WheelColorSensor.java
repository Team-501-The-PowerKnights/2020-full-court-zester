/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Provides implementation of <code>IWheelColorSensor</code> for the
 * <i>Real-Bot</i> which is based on the REV Robotics color sensor.
 */
class WheelColorSensor extends SuitcaseWheelColorSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelColorSensor.class.getName());

    WheelColorSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
