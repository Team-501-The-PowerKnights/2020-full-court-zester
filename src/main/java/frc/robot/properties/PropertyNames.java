/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.properties;

import frc.robot.sensors.SensorNames;
import frc.robot.subsystems.SubsystemNames;

/**
 * Add your docs here.
 */
public class PropertyNames {

    public final class Robot {
        public static final String name = "Robot";
    }

    /***************
     * Drive
     ***************/

    public final class Drive {
        public static final String name = SubsystemNames.driveName;
    }

    public final class Gyro {
        public static final String name = SensorNames.gyroName;
    }

    /***************
     * Power Cells
     ***************/

    public final class Intake {
        public static final String name = SubsystemNames.intakeName;
    }

    public final class Hopper {
        public static final String name = SubsystemNames.hopperName;
    }

    public final class Ballevator {
        public static final String name = SubsystemNames.ballevatorName;
    }

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;
    }

    public final class Turret {
        public static final String name = SubsystemNames.turretName;
    }

    public final class TurretPosition {
        public static final String name = SensorNames.turretLocationName;
    }

    /***************
     * Control Panel
     ***************/

    public final class Wheel {
        public static final String name = SubsystemNames.wheelName;
    }

    public final class WheelColor {
        public static final String name = SensorNames.wheelColorName;
    }

    /***************
     * Climber
     ***************/

    public final class Climber {
        public static final String name = SubsystemNames.climberName;
    }

    /***************
     * Vision
     ***************/

    public final class Vision {
        public static final String name = SensorNames.visionName;
    }

}
