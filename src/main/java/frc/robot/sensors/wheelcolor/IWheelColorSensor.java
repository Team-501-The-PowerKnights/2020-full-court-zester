/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.sensors.ISensor;

/**
 * Add your docs here.
 **/
public interface IWheelColorSensor extends ISensor {

    /**
     * Method providing a handle to access color values within the class.
     * 
     * @return The color the sensor is seeing
     */
    public Color getColor();

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
