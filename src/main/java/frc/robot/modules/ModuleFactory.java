/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.modules.pdp.PDPFactory;
import frc.robot.modules.rpi.RPIFactory;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ModuleFactory {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final Logger logger = RioLogger.getLogger(ModuleFactory.class.getName());

    public static List<IModule> constructModules() {

        ArrayList<IModule> modules = new ArrayList<IModule>();

        TelemetryManager tlmMgr = TelemetryManager.getInstance();

        SmartDashboard.putNumber(TelemetryNames.PDP.status, PKStatus.unknown.tlmValue);
        {
            PDPFactory.constructInstance();
            IModule m = PDPFactory.getInstance();
            tlmMgr.addProvider(m);
            modules.add(m);
        }

        SmartDashboard.putNumber(TelemetryNames.RPI.status, PKStatus.unknown.tlmValue);
        {
            RPIFactory.constructInstance();
            IModule m = RPIFactory.getInstance();
            tlmMgr.addProvider(m);
            modules.add(m);
        }

        return modules;
    }

}
