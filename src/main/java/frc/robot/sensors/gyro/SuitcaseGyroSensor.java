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
class SuitcaseGyroSensor extends BaseGyroSensor {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(SuitcaseGyroSensor.class.getName());

    // Handle to the hardware sensor
    private final AHRSGyro mySensor;

    SuitcaseGyroSensor() {
        logger.info("constructing");

        mySensor = new AHRSGyro();

        logger.info("constructed");
    }

    @Override
    public double getRoll() {
        return mySensor.ahrs.getRoll();
    }

    @Override
    public double getPitch() {
        return mySensor.ahrs.getPitch();
    }

    @Override
    public double getYaw() {
        return mySensor.ahrs.getYaw();
    }

    @Override
    public double getAngle() {
        return mySensor.ahrs.getAngle();
    }

}
