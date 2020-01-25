/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import frc.robot.telemetry.ITelemetryProvider;

/**
 * Add your docs here.
 */
public interface ISensor extends ITelemetryProvider {

    /**
     * Called to update any preferences associated with the sensor. This will be
     * used at a minimum to update any PID values.
     **/
    public void updatePreferences();

    /**
     * Disable the sensor.
     **/
    public void disable();

}
