/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.preferences;

import frc.robot.subsystems.SubsystemNames;

/**
 * Add your docs here.
 */
public class PreferenceNames {

    public final class Drive {
        public static final String name = SubsystemNames.driveName;
        public static final String pid_P = name + ".P";
        public static final String pid_I = name + ".I";
        public static final String pid_D = name + ".D";
        public static final String pid_F = name + ".F";
    }

    public final class Turret {
        public static final String name = SubsystemNames.turretName;
        // PID for turret position
        public static final String pid_P = name + ".P";
        public static final String pid_I = name + ".I";
        public static final String pid_D = name + ".D";
        public static final String pid_F = name + ".F";
    }

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;
        // PID for shooter speed
        public static final String pid_P = name + ".P";
        public static final String pid_I = name + ".I";
        public static final String pid_D = name + ".D";
        public static final String pid_F = name + ".F";
        // Scale applied to shooter speed when in manual
        public static final String scale = name + ".scale";
    }

}
