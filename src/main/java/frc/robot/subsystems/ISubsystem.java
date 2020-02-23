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
 * Note that this now extends the official <i>WPILib</i> interface of
 * <code>Subsystem</code>.
 *
 * @see edu.wpi.first.wpilibj2.command.Subsystem
 **/
public interface ISubsystem extends Subsystem, ITelemetryProvider {

    /**
     * Called to set the default command for the subsystem; the value is determined
     * from the properties file, and loaded dynamically.
     **/
    public void loadDefaultCommand();

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
