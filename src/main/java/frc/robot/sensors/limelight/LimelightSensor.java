/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.limelight;

import org.slf4j.Logger;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * Provides implementation of <code>ILimelightSensor</code> for the
 * <i>Real-Bot</i>.
 */
class LimelightSensor extends BaseLimelightSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(LimelightSensor.class.getName());

    private final NetworkTable table;

    LimelightSensor() {
        logger.info("constructing");

        table = NetworkTableInstance.getDefault().getTable("limelight");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Limelight.x, table.getEntry("tx").getDouble(0.0));
        SmartDashboard.putNumber(TelemetryNames.Limelight.y, table.getEntry("ty").getDouble(0.0));
        SmartDashboard.putNumber(TelemetryNames.Limelight.area, table.getEntry("ta").getDouble(0.0));
        SmartDashboard.putBoolean(TelemetryNames.Limelight.locked, table.getEntry("tv").getDouble(0.0) == 1);
        SmartDashboard.putBoolean(TelemetryNames.Limelight.ledOn, table.getEntry("ledMode").getDouble(1) == 3);
        SmartDashboard.putBoolean(TelemetryNames.Limelight.enabled, table.getEntry("camMode").getDouble(1) == 0);
    }

    @Override
    public void enable() {
        table.getEntry("ledMode").setDouble(3);
        table.getEntry("camMode").setDouble(0);
    }

    @Override
    public void disable() {
        table.getEntry("ledMode").setDouble(1);
        table.getEntry("camMode").setDouble(1);
    }

    @Override
    public double getError() {
        double x = table.getEntry("tx").getDouble(0.0);

        if (table.getEntry("tv").getDouble(0.0) == 1) {
            return -x;
        } else {
            return 0.0;
        }
    }

}
