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
 * Provides implementation of <code>IGyroSensor</code> which has no sensor or
 * other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
class StubGyroSensor extends BaseGyroSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubGyroSensor.class.getName());

    static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubGyroSensor();

        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, true);
    }

    static IGyroSensor getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    StubGyroSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updatePreferences() {
        // Nothing to do here
    }

    @Override
    public void disable() {
        // Nothing to do here
    }

    @Override
    public double getRoll() {
        return 0;
    }

    @Override
    public double getPitch() {
        return 0;
    }

    @Override
    public double getYaw() {
        return 0;
    }

    @Override
    public double getAngle() {
        return 0;
    }

}
