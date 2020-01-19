/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    public final class OIConstants {
        public static final int speedAxis = 1; // Left Y
        public static final int turnAxis = 4; // Right X
    }

    public final class DriveConstants {
        public static final double kS = 0.16; // Volts
        public static final double kV = 2.73; // VoltSeconds Per Meter
        public static final double kA = 0.32; // VoltSecondsSquared Per Meter
        public static final double kP = 2.55; // Drive Velocity
        public static final double trackWidth = 0.568; // Meters
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
        public static final double kMaxSpeed = 3.04; // Meters Per Second
        public static final double kMaxAcceleration = 0.5; // Meters Per Second Squared
        public static final boolean kGyroReversed = true;
        public static final boolean kLeftReversed = false;
        public static final boolean kRightReversed = false;
    }

    public final class ShooterConstants {

        public final class Flywheel {
            public static final double kFlywheelVPGearing = 1;
            public static final double kFlywheelBeltGearing = 1;
            public static final double kCountsPerRevolution = 1;
            public static final double kP = 0;
            public static final double kI = 0;
            public static final double kD = 0;
            public static final double kF = 0;
        }

        public final class Turret {
            public static final double kMaxAngle = 270;
            public static final double kMinAngle = 0;
            public static final double kP = 0;
            public static final double kI = 0;
            public static final double kD = 0;
            public static final double kF = 0;
        }
    }
}
