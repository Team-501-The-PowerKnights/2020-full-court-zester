/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * Provides implementation of <code>IGyroSensor</code> for the
 * <i>Suitcase-Bot</i> which is based on the navX-MXP sensor.
 */
class ProtoGyroSensor extends SuitcaseGyroSensor {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(ProtoGyroSensor.class.getName());

    static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new ProtoGyroSensor();

        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, true);
    }

    static IGyroSensor getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    protected ProtoGyroSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

}
