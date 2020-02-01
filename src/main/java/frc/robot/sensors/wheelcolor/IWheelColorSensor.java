/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import frc.robot.sensors.ISensor;
import frc.robot.utils.PKColor;

/**
 * Add your docs here.
 **/
public interface IWheelColorSensor extends ISensor {

    /**
     * Returns the color currently detected by the sensor.
     * 
     * @return Color the sensor is seeing
     */
    public PKColor getColor();

    /**
     * Returns the confidence associated with the last query for the currently
     * detected color.
     * 
     * @return Confidence in the returned color
     */
    public double getConfidence();

    /**
     * 
     * @return if the sensor senses that the wheel is on blue
     */
    public boolean isBlue();

    /**
     * 
     * @return if the sensor senses that the wheel is on green
     */
    public boolean isGreen();

    /**
     * 
     * @return if the sensor senses that the wheel is on yellow
     */
    public boolean isYellow();

    /**
     * 
     * @return if the sensor senses that the wheel is on red
     */
    public boolean isRed();

}
