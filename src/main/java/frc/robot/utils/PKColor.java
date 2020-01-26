/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class PKColor extends Color {

    public PKColor(double red, double green, double blue) {
        super(red, green, blue);
    }

    public static final Color blueTarget = new PKColor(0.143, 0.427, 0.429);

    public static final Color greenTarget = new PKColor(0.197, 0.561, 0.240);

    public static final Color yellowTarget = new PKColor(0.361, 0.524, 0.113);

    public static final Color redTarget = new PKColor(0.561, 0.232, 0.114);
}
