/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.SensorNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * 
 */
public class GyroFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(GyroFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IGyroSensor ourInstance;
    /** Name of our subsystem **/
    private static final String myName = SensorNames.gyroName;

    /**
     * Constructs instance of the subsystem. Assumed to be called before any usage
     * of the sensor; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's sensors.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        // FIXME - Replace with file based configuration
        final String gyroClassName = "StubGyroSensor";

        switch (gyroClassName) {

        case "GyroSensor":
            logger.info("constructing real {} sensor", myName);
            GyroSensor.constructInstance();
            ourInstance = GyroSensor.getInstance();
            break;

        case "ProtoGyroSensor":
            logger.info("constructing proto {} sensor", myName);
            ProtoGyroSensor.constructInstance();
            ourInstance = ProtoGyroSensor.getInstance();
            break;

        case "SuitcaseGyroSensor":
            logger.info("constructing suitcase {} sensor", myName);
            SuitcaseGyroSensor.constructInstance();
            ourInstance = SuitcaseGyroSensor.getInstance();
            break;

        case "StubGyroSensor":
            logger.info("constructing stub {} sensor", myName);
            StubGyroSensor.constructInstance();
            ourInstance = StubGyroSensor.getInstance();
            break;

        default:
            logger.warn("constructing stub {} sensor", myName);
            StubGyroSensor.constructInstance();
            ourInstance = StubGyroSensor.getInstance();
            break;
        }

        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, true);
    }

    /**
     * Returns the singleton instance of the sensor in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of sensor
     **/
    public static IGyroSensor getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

}
