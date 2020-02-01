/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.telemetry.TelemetryNames;

/**
 * Add your docs here.
 */
public abstract class BaseDriveSubsystem extends SubsystemBase implements IDriveSubsystem {

    protected static final String myName = TelemetryNames.Drive.name;

    protected static IDriveSubsystem ourInstance;
}
