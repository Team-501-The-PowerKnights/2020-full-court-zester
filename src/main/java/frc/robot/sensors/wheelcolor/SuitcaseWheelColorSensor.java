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

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.I2C;

import frc.robot.utils.PKColor;

import riolog.RioLogger;

/**
 * Provides implementation of <code>IWheelColorSensor</code> for the
 * <i>Suitcase-Bot</i> which is based on the REV Robotics color sensor.
 */
class SuitcaseWheelColorSensor extends BaseWheelColorSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(SuitcaseWheelColorSensor.class.getName());

    // Handle to the hardware sensor
    private final ColorSensorV3 mySensor;

    // Set of colors from game we are trying to match
    private final ColorMatch targetMatches;

    // Last retreived result
    private ColorMatchResult result;

    SuitcaseWheelColorSensor() {
        logger.info("constructing");

        mySensor = new ColorSensorV3(I2C.Port.kOnboard);

        targetMatches = new ColorMatch();
        targetMatches.addColorMatch(PKColor.blueTarget);
        targetMatches.addColorMatch(PKColor.greenTarget);
        targetMatches.addColorMatch(PKColor.yellowTarget);
        targetMatches.addColorMatch(PKColor.redTarget);

        // Seed the first result so not null
        // TODO - Verify the sensor comes on line this quickly
        result = targetMatches.matchClosestColor(mySensor.getColor());

        logger.info("constructed");
    }

    @Override
    public PKColor getColor() {
        color = mySensor.getColor();
        result = targetMatches.matchClosestColor(color);

        // FIXME - This throws occasional exception
        PKColor pkColor;
        try {
            pkColor = (PKColor) result.color;
        } catch (ClassCastException ex) {
            logger.error("class of result: {}", result.color, ex);
            pkColor = PKColor.invalidTarget;
        }
        return pkColor;
    }

    @Override
    public double getConfidence() {
        return result.confidence;
    }

}
