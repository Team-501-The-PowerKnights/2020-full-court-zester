/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKColor;

import riolog.RioLogger;

public class StubWheelColorSensor extends BaseWheelColorSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubWheelColorSensor.class.getName());

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.WheelColor.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubWheelColorSensor();

        SmartDashboard.putBoolean(TelemetryNames.WheelColor.status, true);
    }

    public static IWheelColorSensor getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    public StubWheelColorSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updatePreferences() {
    }

    @Override
    public void disable() {
    }

    @Override
    public PKColor getColor() {
        return PKColor.invalidTarget;
    }

    @Override
    public double getConfidence() {
        return 0.0;
    }

}
