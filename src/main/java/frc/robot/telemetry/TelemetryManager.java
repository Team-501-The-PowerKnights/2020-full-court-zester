/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.telemetry;

import java.util.ArrayList;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.telemetry.TelemetryNames.Misc;
import frc.robot.utils.PKStatus;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class TelemetryManager {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(TelemetryManager.class.getName());

    /** Singleton instance of class for all to use **/
    private static TelemetryManager ourInstance;
    /** Name of our subsystem **/
    private final static String myName = TelemetryNames.Telemetry.name;

    private final ArrayList<ITelemetryProvider> providerList;

    public static synchronized void constructInstance() {
        SmartDashboard.putNumber(TelemetryNames.Telemetry.status, PKStatus.unknown.tlmValue);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        SmartDashboard.putNumber(TelemetryNames.Telemetry.status, PKStatus.inProgress.tlmValue);

        ourInstance = new TelemetryManager();

        SmartDashboard.putNumber(TelemetryNames.Telemetry.status, PKStatus.success.tlmValue);
    }

    public static TelemetryManager getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }
        return ourInstance;
    }

    private TelemetryManager() {
        logger.info("constructing");

        providerList = new ArrayList<ITelemetryProvider>();

        logger.info("constructed");
    }

    public void addProvider(ITelemetryProvider provider) {
        if (provider != null) {
            providerList.add(provider);
        }
    }

    private int counter = 0;

    // This should get called from robotPeriodic() in robot
    public void sendTelemetry() {
        if (counter >= 10) {
            counter = 0;

            SmartDashboard.putBoolean(Misc.fmsConnected, OI.getInstance().isFieldConnected());

            for (ITelemetryProvider provider : providerList) {
                // logger.trace("calling w/ provider {}", provider);
                provider.updateTelemetry();
            }
        } else {
            counter++;
        }
    }

}
