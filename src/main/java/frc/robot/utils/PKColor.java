/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.util.Color;

import riolog.RioLogger;

/**
 * This class extends the standard <code>WPILib</code> implementation to add a
 * string for the name. This provides a quicker 'lookup' when doing telemetry
 * reporting or logging. It also adds a telemetry value that can be used with
 * the Dashboard color widget that we extended.
 * 
 * The RGB values used to create the colors are from RevRobotics and are tuned
 * to their sensor and the hues for the color wheel used in this years game.
 */
public class PKColor extends Color {

    /* Our classes logger */
    @SuppressWarnings("unused")
    private static final Logger logger = RioLogger.getLogger(PKColor.class.getName());

    public final String name;

    public final double tlmValue;

    public PKColor(double red, double green, double blue, String name, double tlmValue) {
        super(red, green, blue);
        this.name = name;
        this.tlmValue = tlmValue;
    }

    public static final PKColor blueTarget = new PKColor(0.143, 0.427, 0.429, "blue", 13);

    public static final PKColor greenTarget = new PKColor(0.197, 0.561, 0.240, "green", 12);

    public static final PKColor yellowTarget = new PKColor(0.361, 0.524, 0.113, "yellow", 11);

    public static final PKColor redTarget = new PKColor(0.561, 0.232, 0.114, "red", 10);

    /**
     * A constant to use for invalid colors (in case a match solution isn't found).
     * This maps to "black" in the widget.
     */
    public static final PKColor invalidTarget = new PKColor(0, 0, 0, "invalid", 0);

}
