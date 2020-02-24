/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.home;

import org.slf4j.Logger;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

abstract class BaseHomeSensor implements IHomeSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseHomeSensor.class.getName());

    protected static final String myName = TelemetryNames.Home.name;

    BaseHomeSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
