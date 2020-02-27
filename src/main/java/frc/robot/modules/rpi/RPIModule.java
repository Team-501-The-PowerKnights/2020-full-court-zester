/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules.rpi;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
class RPIModule extends BaseRPIModule {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(RPIModule.class.getName());

    public RPIModule() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // FIXME - Find the network table to pull from
        SmartDashboard.putNumber(TelemetryNames.RPI.clockSpeed, 0);
        SmartDashboard.putNumber(TelemetryNames.RPI.freeMemory, 0);
        SmartDashboard.putNumber(TelemetryNames.RPI.socTemp, 0);
    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

}
