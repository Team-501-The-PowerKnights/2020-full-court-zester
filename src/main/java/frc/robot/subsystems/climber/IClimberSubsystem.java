/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IClimberSubsystem extends ISubsystem {

    public void stop();

    /**
     * Extend in preparation for the climb
     **/
    public void extend();

    /**
     * Pull in to actually lift robot and climb
     **/
    public void climb();

    /**
     * Run the motors in reverse to retract and arm. Should only be run in the pit.
     **/
    public void retract();

}
