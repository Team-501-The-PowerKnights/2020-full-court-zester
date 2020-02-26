/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.turretlocation;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

abstract class BaseTurretLocationSensor implements ITurretLocationSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseTurretLocationSensor.class.getName());

    /** Our sensor's name **/
    protected static final String myName = TelemetryNames.TurretLocation.name;

    BaseTurretLocationSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.TurretLocation.isFound, get());
    }

}
