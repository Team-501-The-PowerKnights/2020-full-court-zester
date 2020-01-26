/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKColor;

public class WheelColorSensor implements IWheelColorSensor {

    private static final String myName = TelemetryNames.WheelColor.name;

    private static IWheelColorSensor ourInstance;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.WheelColor.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new WheelColorSensor();

        SmartDashboard.putBoolean(TelemetryNames.WheelColor.status, true);
    }

    public static IWheelColorSensor getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    private final ColorSensorV3 mySensor;
    private final ColorMatch match;

    public WheelColorSensor() {
        mySensor = new ColorSensorV3(I2C.Port.kOnboard);

        match = new ColorMatch();
        match.addColorMatch(PKColor.blueTarget);
        match.addColorMatch(PKColor.greenTarget);
        match.addColorMatch(PKColor.yellowTarget);
        match.addColorMatch(PKColor.redTarget);
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
        SmartDashboard.putString(TelemetryNames.WheelColor.color, parseTarget(getColor()));
    }

    @Override
    public Color getColor() {
        return match.matchClosestColor(mySensor.getColor()).color;
    }

    @Override
    public boolean isBlue() {
        return getColor().equals(PKColor.blueTarget);
    }

    @Override
    public boolean isGreen() {
        return getColor().equals(PKColor.greenTarget);
    }

    @Override
    public boolean isRed() {
        return getColor().equals(PKColor.redTarget);
    }

    @Override
    public boolean isYellow() {
        return getColor().equals(PKColor.yellowTarget);
    }

    private String parseTarget(Color color) {
        String retColor;

        if (color == PKColor.blueTarget) {
            retColor = "Blue";
        } else if (color == PKColor.greenTarget) {
            retColor = "Green";
        } else if (color == PKColor.yellowTarget) {
            retColor = "Yellow";
        } else if (color == PKColor.redTarget) {
            retColor = "Red";
        } else {
            retColor = "Unknown";
        }

        return retColor;
    }

}
