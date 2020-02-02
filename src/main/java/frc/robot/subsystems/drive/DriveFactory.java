/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.drive.DriveBaseCommand;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * 
 */
public class DriveFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IDriveSubsystem ourInstance;
    /** Name of our subsystem **/
    private static final String myName = SubsystemNames.driveName;

    /**
     * Constructs instance of the subsystem. Assumed to be called before any usage
     * of the subsystem; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's subsystems.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Drive.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        // FIXME - Replace with file based configuration
        final String driveClassName = "ProtoDriveSubsystem";

        switch (driveClassName) {

        case "DriveSubsystem":
            logger.info("constructing real {} subsystem", myName);
            DriveSubsystem.constructInstance();
            ourInstance = DriveSubsystem.getInstance();
            break;

        case "ProtoDriveSubsystem":
            logger.info("constructing proto {} subsystem", myName);
            ProtoDriveSubsystem.constructInstance();
            ourInstance = ProtoDriveSubsystem.getInstance();
            ourInstance.setDefaultCommand(new DriveBaseCommand());
            break;

        case "StubDriveSubsystem":
            logger.info("constructing stub {} subsystem", myName);
            SuitcaseDriveSubsystem.constructInstance();
            ourInstance = SuitcaseDriveSubsystem.getInstance();
            break;

        default:
            logger.warn("constructing stub {} subsystem", myName);
            SuitcaseDriveSubsystem.constructInstance();
            ourInstance = SuitcaseDriveSubsystem.getInstance();
            break;
        }

        SmartDashboard.putBoolean(TelemetryNames.Drive.status, true);
    }

    /**
     * Returns the singleton instance of the subsystem in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of subsystem
     **/
    public static IDriveSubsystem getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

    

}
