/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import org.slf4j.Logger;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import riolog.RioLogger;

/**
 * Wrapper for the Limelight Vision Sensor to provide ease when switching from
 * Limelight and RPi vision. Additionally, provides access to the values sent
 * through the NetworkTables by the Limelight.
 */
class LimelightVision {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final Logger logger = RioLogger.getLogger(LimelightVision.class.getName());

    private NetworkTable table;

    boolean limelightValid;

    LimelightVision() {

        table = NetworkTableInstance.getDefault().getTable("limelight");

        limelightValid = (table != null) ? true : false;

        SmartDashboard.putBoolean("limelightValid", limelightValid);

    }

    protected boolean isLocked() {
        return table.getEntry("tv").getDouble(0.0) == 1;
    }

    protected boolean isEnabled() {
        return table.getEntry("camMode").getDouble(1) == 0 && table.getEntry("ledMode").getDouble(1) == 3;
    }

    protected void enable() {
        table.getEntry("ledMode").setDouble(3);
        table.getEntry("camMode").setDouble(0);
    }

    protected void disable() {
        table.getEntry("ledMode").setDouble(1);
        table.getEntry("camMode").setDouble(1);
    }

    protected double getError() {
        double x = table.getEntry("tx").getDouble(0.0);

        if (table.getEntry("tv").getDouble(0.0) == 1) {
            return -x;
        } else {
            return 0.0;
        }
    }

}
