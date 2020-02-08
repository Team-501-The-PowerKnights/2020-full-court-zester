/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.telemetry;

import frc.robot.sensors.SensorNames;
import frc.robot.subsystems.SubsystemNames;

public final class TelemetryNames {

    public final class Misc {
        public static final String name = "Misc";

        public static final String programmer = name + ".programmer";
        public static final String codeVersion = name + ".codeVersion";
    }

    public final class Robot {
        public static final String name = "Robot";

        public static final String robotName = name + ".name";
    }

    /***************
     * Modules
     ***************/
    public final class PDP {
        public static final String name = "PDP";

        public static final String status = name + ".status";
        public static final String busVoltage = name + ".busVoltage";
        public static final String totalCurrent = name + ".totalCurrent";
        public static final String totalEnergy = name + ".totalEnergy";
    }

    /***************
     * Managers
     ***************/

    public final class Telemetry {
        public static final String name = "Telemetry";

        public static final String status = name + ".status";
    }

    public final class Properties {
        public static final String name = "Properties";

        public static final String status = name + ".status";
    }

    public static class Scheduler {
        public static final String name = "Scheduler";

        public static final String status = name + ".status";
        // The current commands running on the robot
        public static final String currentCommands = name + ".currentCommands";
    }

    /***************
     * OI
     ***************/

    public final class OI {
        public static final String name = "OI";

        public static final String status = name + ".status";
    }

    /***************
     * Drive
     ***************/

    public final class Drive {
        public static final String name = SubsystemNames.driveName;

        public static final String status = name + ".status";
    }

    public final class Gyro {
        public static final String name = SensorNames.gyroName;

        public static final String status = name + ".status";
        public static final String roll = name + ".roll";
        public static final String pitch = name + ".pitch";
        public static final String yaw = name + ".yaw";
        public static final String angle = name + ".angle";
        public static final String heading = name + ".heading";
    }

    /***************
     * Power Cells
     ***************/

    public final class Intake {
        public static final String name = SubsystemNames.intakeName;

        public static final String status = name + ".status";
    }

    public final class Hopper {
        public static final String name = SubsystemNames.hopperName;

        public static final String status = name + ".status";
    }

    public final class Ballevator {
        public static final String name = SubsystemNames.ballevatorName;

        public static final String status = name + ".status";
    }

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;

        public static final String status = name + ".status";
        public static final String angle = name + ".angle";
    }

    /***************
     * Control Panel
     ***************/

    public final class Wheel {
        public static final String name = SubsystemNames.wheelName;

        public static final String status = name + ".status";
    }

    public final class WheelColor {
        public static final String name = SensorNames.wheelColorName;

        public static final String status = name + ".status";
        // Raw color returned from sensor
        public static final String color = name + ".color";
        // Filtered matched color returned from software
        public static final String match = name + ".match";
        // Confidence associated with matched color
        public static final String confidence = name + ".confidence";
    }

    /***************
     * Climber
     ***************/

    public final class Climber {
        public static final String name = SubsystemNames.climberName;

        public static final String status = name + ".status";
    }

}