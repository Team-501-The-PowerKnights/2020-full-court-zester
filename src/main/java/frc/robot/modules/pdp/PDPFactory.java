/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules.pdp;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.modules.IModule;
import frc.robot.modules.ModuleNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * 
 */
public class PDPFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PDPFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IModule ourInstance;
    /** Name of our module **/
    private static final String myName = ModuleNames.pdpName;

    /**
     * Constructs instance of the module. Assumed to be called before any usage of
     * the module; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's modules.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.PDP.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        loadImplementationClass();
    }

    private static void loadImplementationClass() {
        // FIXME - Replace with file based configuration
        final String myClassName = "PDPModule";

        String myPkgName = PDPFactory.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} module", myClassName, myName);
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourInstance = (IModule) myObject;
            // TODO - make this multi-state, this would be "success" / green
            SmartDashboard.putBoolean(TelemetryNames.PDP.status, true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourInstance = new StubPDPModule();
            // TODO - make this multi-state, this would "degraded" / yellow
            SmartDashboard.putBoolean(TelemetryNames.PDP.status, true);
        }
    }

    /**
     * Returns the singleton instance of the module in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of module
     **/
    public static IModule getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

}
