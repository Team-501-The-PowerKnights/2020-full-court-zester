/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.util.Color;

/**
 * This class extends the standard <code>WPILib</code> implementation to add a
 * string for the name. This provides a quicker 'lookup' when doing telemetry
 * reporting or logging.
 * 
 * The RGB values used to create the colors are from RevRobotics and are tuned
 * to their sensor and the hues for the color wheel used in this years game.
 */
public class PKColor extends Color {

    public final String name;

    public PKColor(double red, double green, double blue, String name) {
        super(red, green, blue);
        this.name = name;
    }

    public static final PKColor blueTarget = new PKColor(0.143, 0.427, 0.429, "blue");

    public static final PKColor greenTarget = new PKColor(0.197, 0.561, 0.240, "green");

    public static final PKColor yellowTarget = new PKColor(0.361, 0.524, 0.113, "yellow");

    public static final PKColor redTarget = new PKColor(0.561, 0.232, 0.114, "red");

    /**
     * A constant to use for invalid colors (in case a match solution isn't found).
     */
    public static final PKColor invalidTarget = new PKColor(0, 0, 0, "invalid");

}
