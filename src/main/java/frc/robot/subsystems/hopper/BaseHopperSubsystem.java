/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.hopper;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class BaseHopperSubsystem extends SubsystemBase implements IHopperSubsystem {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final Logger logger = RioLogger.getLogger(BaseHopperSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = TelemetryNames.Hopper.name;

}
