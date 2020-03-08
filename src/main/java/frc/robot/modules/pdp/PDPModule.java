/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules.pdp;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

class PDPModule extends BasePDPModule {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PDPModule.class.getName());

    // Handle to our module
    private final PowerDistributionPanel pdp;

    public PDPModule() {
        logger.info("constructing");

        pdp = new PowerDistributionPanel(0);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.PDP.busVoltage, getBusVoltage());
        SmartDashboard.putNumber(TelemetryNames.PDP.totalCurrent, getTotalEnergy());
        SmartDashboard.putNumber(TelemetryNames.PDP.totalEnergy, getTotalEnergy());
    }

    @Override
    public double getBusVoltage() {
        return pdp.getVoltage();
    }

    @Override
    public double getTotalCurrent() {
        return pdp.getTotalCurrent();
    }

    @Override
    public double getTotalEnergy() {
        return pdp.getTotalEnergy();
    }

    @Override
    public double getCurrent(int deviceID) {
        return pdp.getCurrent(deviceID);
    }

}
