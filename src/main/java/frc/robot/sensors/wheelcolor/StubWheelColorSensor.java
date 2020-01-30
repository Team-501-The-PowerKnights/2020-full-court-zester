/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

public class StubWheelColorSensor implements IWheelColorSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubWheelColorSensor.class.getName());

    private static final String myName = TelemetryNames.WheelColor.name;

    private static IWheelColorSensor ourInstance;

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
    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTelemetry() {
        // TODO - Implement color telemetry as a string
        SmartDashboard.putString(TelemetryNames.WheelColor.color, getColor().toString());
    }

    @Override
    public Color getColor() {
        return Color.kBlue;
    }

    @Override
    public boolean isBlue() {
        return getColor().equals(Color.kBlue);
    }

    @Override
    public boolean isGreen() {
        return getColor().equals(Color.kGreen);
    }

    @Override
    public boolean isYellow() {
        return getColor().equals(Color.kYellow);
    }

    @Override
    public boolean isRed() {
        return getColor().equals(Color.kRed);
    }

}
