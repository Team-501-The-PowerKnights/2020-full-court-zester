/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.telemetry.TelemetryNames;

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

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public WheelColorSensor() {
        mySensor = new ColorSensorV3(I2C.Port.kOnboard);

        match = new ColorMatch();
        match.addColorMatch(kBlueTarget);
        match.addColorMatch(kGreenTarget);
        match.addColorMatch(kRedTarget);
        match.addColorMatch(kYellowTarget);
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
        String color;

        ColorMatchResult matchResult = match.matchClosestColor(getColor());
        
        if (matchResult.color == kBlueTarget) {
            color = "Blue";
          } else if (matchResult.color == kRedTarget) {
            color = "Red";
          } else if (matchResult.color == kGreenTarget) {
            color = "Green";
          } else if (matchResult.color == kYellowTarget) {
            color = "Yellow";
          } else {
            color = "Unknown";
        }

        SmartDashboard.putString(TelemetryNames.WheelColor.color, color);
    }

    @Override
    public Color getColor() {
        return mySensor.getColor();
    }

    @Override
    public boolean isBlue() {
        return getColor().equals(kBlueTarget);
    }

    @Override
    public boolean isGreen() {
        return getColor().equals(kGreenTarget);
    }

    @Override
    public boolean isRed() {
        return getColor().equals(kRedTarget);
    }

    @Override
    public boolean isYellow() {
        return getColor().equals(kYellowTarget);
    }

}
