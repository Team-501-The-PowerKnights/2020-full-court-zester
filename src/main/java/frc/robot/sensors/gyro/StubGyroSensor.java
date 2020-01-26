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

class StubGyroSensor implements IGyroSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubGyroSensor.class.getName());

    private static final String myName = TelemetryNames.Gyro.name;

    private static IGyroSensor ourInstance;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubGyroSensor();

        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, true);
    }

    public static IGyroSensor getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    public StubGyroSensor() {
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
    public void updateTelemetry() {
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
    public double getHeading() {
        return 0;
    }

    @Override
    public double getAngle() {
        return 0;
    }

}
