/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ballevator.BallevatorFactory;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.hopper.HopperFactory;
import frc.robot.subsystems.intake.IntakeFactory;
import frc.robot.subsystems.shooter.ShooterFactory;
import frc.robot.subsystems.wheel.WheelFactory;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.telemetry.TelemetryManager;

import riolog.RioLogger;

/**
 * 
 **/
public class SubsystemFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(SubsystemFactory.class.getName());

    public static List<ISubsystem> constructSubsystems() {
        logger.info("constructing");

        ArrayList<ISubsystem> subsystems = new ArrayList<ISubsystem>();

        TelemetryManager tlmMgr = TelemetryManager.getInstance();

        /***************
         * Drive
         ***************/

        SmartDashboard.putBoolean(TelemetryNames.Drive.status, false);
        {
            DriveFactory.constructInstance();
            ISubsystem ss = DriveFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        /***************
         * Power Cells
         ***************/

        SmartDashboard.putBoolean(TelemetryNames.Intake.status, false);
        {
            IntakeFactory.constructInstance();
            ISubsystem ss = IntakeFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        SmartDashboard.putBoolean(TelemetryNames.Hopper.status, false);
        {
            HopperFactory.constructInstance();
            ISubsystem ss = HopperFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        SmartDashboard.putBoolean(TelemetryNames.Ballevator.status, false);
        {
            BallevatorFactory.constructInstance();
            ISubsystem ss = BallevatorFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        SmartDashboard.putBoolean(TelemetryNames.Shooter.status, false);
        {
            ShooterFactory.constructInstance();
            ISubsystem ss = ShooterFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        /***************
         * Control Panel
         ***************/

        SmartDashboard.putBoolean(TelemetryNames.Wheel.status, false);
        {
            WheelFactory.constructInstance();
            ISubsystem ss = WheelFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        /***************
         * Climber
         ***************/

        SmartDashboard.putBoolean(TelemetryNames.Climber.status, false);
        {
            ClimberFactory.constructInstance();
            ISubsystem ss = ClimberFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        logger.info("constructed");
        return subsystems;
    }

}
