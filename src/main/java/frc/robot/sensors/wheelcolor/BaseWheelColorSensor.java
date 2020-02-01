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
import frc.robot.utils.PKColor;

import riolog.RioLogger;

abstract class BaseWheelColorSensor implements IWheelColorSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseWheelColorSensor.class.getName());

    protected static final String myName = TelemetryNames.WheelColor.name;

    // Last retreived color (in raw form from sensor)
    protected Color color;

    BaseWheelColorSensor() {
        logger.info("constructing");

        // Seed first instance so won't be null
        color = Color.kBlack;

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // Call getColor first so that the state of the sensor is updated
        SmartDashboard.putString(TelemetryNames.WheelColor.match, getColor().name);
        SmartDashboard.putNumber(TelemetryNames.WheelColor.confidence, getConfidence());
        SmartDashboard.putString(TelemetryNames.WheelColor.color, color.toString());
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

}
