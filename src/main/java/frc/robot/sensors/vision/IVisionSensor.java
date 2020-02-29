/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import frc.robot.sensors.ISensor;

/**
 * Add your docs here.
 **/
public interface IVisionSensor extends ISensor {

    /**
     * Whether the vision sensor is active or not?
     * 
     * @return
     **/
    public boolean isActive();

    public double getError();

    public double getY();

    public boolean isLocked();

}
