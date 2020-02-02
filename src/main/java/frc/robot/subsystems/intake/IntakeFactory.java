/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import org.slf4j.Logger;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * 
 */
public class IntakeFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IntakeFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IIntakeSubsystem ourInstance;
    /** Name of our subsystem **/
    private static final String myName = SubsystemNames.intakeName;

    /**
     * Constructs instance of the subsystem. Assumed to be called before any usage
     * of the subsystem; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's subsystems.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Intake.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        // FIXME - Replace with file based configuration
        final String intakeClassName = "StubIntakeSubsystem";

        switch (intakeClassName) {

        case "IntakeSubsystem":
            logger.info("constructing real {} subsystem", myName);
            IntakeSubsystem.constructInstance();
            ourInstance = IntakeSubsystem.getInstance();
            break;

        case "StubIntakeSubsystem":
            logger.info("constructing stub {} subsystem", myName);
            StubIntakeSubsystem.constructInstance();
            ourInstance = StubIntakeSubsystem.getInstance();
            break;

        default:
            logger.warn("constructing stub {} subsystem", myName);
            StubIntakeSubsystem.constructInstance();
            ourInstance = StubIntakeSubsystem.getInstance();
            break;
        }

        SmartDashboard.putBoolean(TelemetryNames.Intake.status, true);
    }

    /**
     * Returns the singleton instance of the subsystem in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of subsystem
     **/
    public static IIntakeSubsystem getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

    

}
