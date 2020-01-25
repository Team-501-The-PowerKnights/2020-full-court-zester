/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.telemetry.ITelemetryProvider;

/**
 * 
 **/
public interface ISubsystem extends Subsystem, ITelemetryProvider {

    /**
     * Returns the a reference to the subsystem, but in a <code>class</code>
     * that the <i>WPILib</i> interface wants.
     *
     * @return instance of subsystem with right typecase
     * 
     * @see edu.wpi.first.wpilibj2.command.Subsystem
     */
    public Subsystem getWpiSubsystem();

    /**
     * Called to validate that the calibration values in the properties match the
     * physical values from the subsystem itself.
     **/
    public void validateCalibration();

    /**
     * Called to update any preferences associated with the subsystem. This will be
     * used at a minimum to update any PID values.
     **/
    public void updatePreferences();

    /**
     * Disable any active components in the subsystem.
     **/
    public void disable();

}
