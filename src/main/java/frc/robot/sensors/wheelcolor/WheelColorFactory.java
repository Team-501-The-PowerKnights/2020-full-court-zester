/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.SensorNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * 
 */
public class WheelColorFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelColorFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IWheelColorSensor ourInstance;
    /** Name of our subsystem **/
    private static final String myName = SensorNames.wheelColorName;

    /**
     * Constructs instance of the subsystem. Assumed to be called before any usage
     * of the sensor; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's sensors.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.WheelColor.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        // FIXME - Replace with file based configuration
        final String myClassName = "SuitcaseWheelColorSensor";

        logger.info("constructing {} for {} sensor", myClassName, myName);
        switch (myClassName) {

        case "WheelColorSensor":
            ourInstance = new WheelColorSensor();
            break;

        case "ProtoColorSensor":
            ourInstance = new ProtoWheelColorSensor();
            break;

        case "SuitcaseWheelColorSensor":
            ourInstance = new SuitcaseWheelColorSensor();
            break;

        case "StubWheelColorSensor":
            ourInstance = new StubWheelColorSensor();
            break;

        default:
            logger.warn("invalid/missing sensor class for ", myName);
            ourInstance = new StubWheelColorSensor();
            break;

        }

        SmartDashboard.putBoolean(TelemetryNames.WheelColor.status, true);
    }

    /**
     * Returns the singleton instance of the sensor in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of sensor
     **/
    public synchronized static IWheelColorSensor getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

}
