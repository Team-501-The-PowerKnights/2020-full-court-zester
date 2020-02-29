/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.SubsystemNames;

/**
 * Add your docs here.
 */
public final class CommandingNames {

    private static final String cmdPrefix = ".cmd-";

    /***************
     * Power Cells
     ***************/

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;

        public static final String speed = name + cmdPrefix + "speed";
        public static final String tolerance = name + cmdPrefix + "tolerance";
    }

}
