/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

abstract class BaseGyroSensor implements IGyroSensor {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(BaseGyroSensor.class.getName());

    /** Our sensor's name **/
    protected static final String myName = TelemetryNames.Gyro.name;

    protected final boolean gyroReversed;

    BaseGyroSensor() {
        logger.info("constructing");

        // FIXME - Get from Properties file
        this.gyroReversed = false;

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Gyro.roll, getRoll());
        SmartDashboard.putNumber(TelemetryNames.Gyro.pitch, getPitch());
        SmartDashboard.putNumber(TelemetryNames.Gyro.yaw, getYaw());

        SmartDashboard.putNumber(TelemetryNames.Gyro.angle, getAngle());
        SmartDashboard.putNumber(TelemetryNames.Gyro.heading, getHeading());
    }

    @Override
    public double getHeading() {
        return Math.IEEEremainder(getAngle(), 360) * (gyroReversed ? -1 : 1);
    }

}
