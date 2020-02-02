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
        public static final String name = SubsystemNames.driveName;
        public static final String p = name + ".P";
        public static final String i = name + ".I";
        public static final String d = name + ".D";
        public static final String f = name + ".F";
    }

}
