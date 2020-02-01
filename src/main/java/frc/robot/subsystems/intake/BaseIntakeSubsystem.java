/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.telemetry.TelemetryNames;

/**
 * Add your docs here.
 */
public abstract class BaseIntakeSubsystem extends SubsystemBase implements IIntakeSubsystem {

    protected static final String myName = TelemetryNames.Intake.name;

    protected static IIntakeSubsystem ourInstance;
}
