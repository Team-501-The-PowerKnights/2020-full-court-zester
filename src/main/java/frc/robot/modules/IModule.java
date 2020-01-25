/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules;

import frc.robot.telemetry.ITelemetryProvider;

/**
 * Add your docs here.
 */
public interface IModule extends ITelemetryProvider {

    /**
     * Called to update any preferences associated with the module. This will be
     * used at a minimum to update any PID values.
     **/
    public void updatePreferences();

    /**
     * Disable the module.
     **/
    public void disable();

}
