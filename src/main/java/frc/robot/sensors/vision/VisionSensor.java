/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

/**
 * Provides implementation of <code>IVisionSensor</code> for the
 * <i>Real-Bot</i>.
 */
class VisionSensor extends BaseVisionSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(VisionSensor.class.getName());

    private LimelightVision mySensor;

    VisionSensor() {
        logger.info("constructing");

        mySensor = new LimelightVision();

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Vision.locked, mySensor.isLocked());
        SmartDashboard.putBoolean(TelemetryNames.Vision.enabled, mySensor.isEnabled());
    }

    @Override
    public void enable() {
        super.enable();

        mySensor.enable();
    }

    @Override
    public void disable() {
        super.disable();

        mySensor.disable();
    }

    @Override
    public double getError() {
        return mySensor.getError();
    }

    @Override
    public double getY() {
        return mySensor.getY();
    }

    @Override
    public boolean isLocked() {
        return mySensor.isLocked();
    }

}
