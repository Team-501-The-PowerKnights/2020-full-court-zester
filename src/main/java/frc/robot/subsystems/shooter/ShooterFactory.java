/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * 
 */
public class ShooterFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IShooterSubsystem ourInstance;
    /** Name of our subsystem **/
    private static final String myName = SubsystemNames.shooterName;

    /**
     * Constructs instance of the subsystem. Assumed to be called before any usage
     * of the subsystem; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's subsystems.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Shooter.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        // FIXME - Replace with file based configuration
        final String ShooterClassName = "SuitcaseShooterSubsystem";

        switch (ShooterClassName) {

        case "ShooterSubsystem":
            break;

        case "ProtoShooterSubsystem":
            logger.info("constructing proto {} subsystem", myName);
            ProtoShooterSubsystem.constructInstance();
            ourInstance = ProtoShooterSubsystem.getInstance();
            break;


        case "SuitcaseShooterSubsystem":
            logger.info("constructing {} subsystem", myName);
            SuitcaseShooterSubsystem.constructInstance();
            ourInstance = SuitcaseShooterSubsystem.getInstance();
            break;

        case "StubShooterSubsystem":
            logger.info("constructing stub {} subsystem", myName);
            StubShooterSubsystem.constructInstance();
            ourInstance = StubShooterSubsystem.getInstance();
            break;

        default:
            logger.warn("constructing stub {} subsystem", myName);
            StubShooterSubsystem.constructInstance();
            ourInstance = StubShooterSubsystem.getInstance();
            break;
        }

        SmartDashboard.putBoolean(TelemetryNames.Shooter.status, true);
    }

    /**
     * Returns the singleton instance of the subsystem in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of subsystem
     **/
    public static IShooterSubsystem getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

    /**
     * Returns the singleton instance of the subsystem, but in a <code>class</code>
     * that the <i>WPILib</i> interface wants.
     *
     * @return singleton instance of subsystem
     */
    public static Subsystem getWpiSubsystem() {
        // TODO - Can we get rid of this now?
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }
        return (Subsystem) ourInstance;
    }

}
