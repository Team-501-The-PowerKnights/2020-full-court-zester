/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
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
