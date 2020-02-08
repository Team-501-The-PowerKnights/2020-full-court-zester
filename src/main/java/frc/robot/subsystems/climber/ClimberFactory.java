/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.climber.ClimberDoNothing;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * 
 */
public class ClimberFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ClimberFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IClimberSubsystem ourInstance;
    /** Name of our subsystem **/
    private static final String myName = SubsystemNames.climberName;

    /**
     * Constructs instance of the subsystem. Assumed to be called before any usage
     * of the subsystem; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's subsystems.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Climber.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        loadImplementationClass();

        loadDefaultCommandClass();
    }

    private static void loadImplementationClass() {
        // FIXME - Replace with file based configuration
        final String myClassName = "StubClimberSubsystem";

        String myPkgName = ClimberFactory.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("factory class to load: {}", classToLoad);

        logger.info("constructing {} for {} sensor", myClassName, myName);
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourInstance = (IClimberSubsystem) myObject;
            // TODO - make this multi-state, this would be "success" / green
            SmartDashboard.putBoolean(TelemetryNames.Climber.status, true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourInstance = new StubClimberSubsystem();
            // TODO - make this multi-state, this would "degraded" / yellow
            SmartDashboard.putBoolean(TelemetryNames.Climber.status, true);
        }
    }

    private static void loadDefaultCommandClass() {
        // FIXME - Replace with file based configuration
        final String myClassName = "ClimberDoNothing";

        String myPkgName = ClimberDoNothing.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("factory class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem default command", myClassName, myName);
        Command ourCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourCommand = (Command) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourCommand = (Command) new ClimberDoNothing();
        }

        ourInstance.setDefaultCommand(ourCommand);
    }

    /**
     * Returns the singleton instance of the subsystem in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of subsystem
     **/
    public static IClimberSubsystem getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

}
