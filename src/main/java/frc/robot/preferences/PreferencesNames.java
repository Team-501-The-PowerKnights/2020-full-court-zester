/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.preferences;

import frc.robot.subsystems.SubsystemNames;

/**
 * Add your docs here.
 */
public class PreferencesNames {

    public final class Drive {
        public static final String name = SubsystemNames.driveName;
        public static final String p = name + ".P";
        public static final String i = name + ".I";
        public static final String d = name + ".D";
        public static final String f = name + ".F";
    }

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;
        // PID for shooter speed
        public static final String p = name + ".shooterP";
        public static final String i = name + ".shooterI";
        public static final String d = name + ".shooterD";
        public static final String f = name + ".shooterF";
        // Scale applied to shooter speed when in manual
        public static final String scale = name + ".scale";

        // PID for turret position
        // PID for shooter speed
        public static final String turretP = name + ".turretP";
        public static final String turretI = name + ".turretI";
        public static final String turretD = name + ".turretD";
        public static final String turretF = name + ".turretF";
    }

    public final class Intake {
        public static final String name = SubsystemNames.intakeName;
        public static final String p = name + ".P";
        public static final String i = name + ".I";
        public static final String d = name + ".D";
        public static final String f = name + ".F";
    }

}
