/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import frc.robot.sensors.ISensor;

/**
 * Add your docs here.
 **/
public interface IGyroSensor extends ISensor {

    /**
     * Returns the raw 'roll' ('Y') value from sensor. This is aligned along the
     * short axis of the board.
     * 
     * @return roll or Y value
     */
    public double getRoll();

    /**
     * Returns the raw 'pitch' ('X') value from sensor. This is aligned along the
     * long axis of the board.
     * 
     * @return pitch or X value
     */
    public double getPitch();

    /**
     * Returns the raw 'yaw' ('Z') value from sensor. This is aligned vertically out
     * of the board.
     * 
     * @return yaw or Z value
     */
    public double getYaw();

    /**
     * 
     * @return
     */
    public double getAngle();

    /**
     * Returns the heading which is defined as the <code>angle</code> but kept
     * between the values of 0.0 and 360.0 degrees no matter how much the robot
     * rotates from a reset.
     * 
     * @return
     */
    public double getHeading();

}
