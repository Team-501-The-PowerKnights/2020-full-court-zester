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
import frc.robot.subsystems.turret.TurretFactory;
import frc.robot.subsystems.wheel.WheelFactory;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.utils.PKStatus;

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

        SmartDashboard.putNumber(TelemetryNames.Drive.status, PKStatus.unknown.tlmValue);
        {
            DriveFactory.constructInstance();
            ISubsystem ss = DriveFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        /***************
         * Power Cells
         ***************/

        SmartDashboard.putNumber(TelemetryNames.Intake.status, PKStatus.unknown.tlmValue);
        {
            IntakeFactory.constructInstance();
            ISubsystem ss = IntakeFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        SmartDashboard.putNumber(TelemetryNames.Hopper.status, PKStatus.unknown.tlmValue);
        {
            HopperFactory.constructInstance();
            ISubsystem ss = HopperFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        SmartDashboard.putNumber(TelemetryNames.Ballevator.status, PKStatus.unknown.tlmValue);
        {
            BallevatorFactory.constructInstance();
            ISubsystem ss = BallevatorFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        SmartDashboard.putNumber(TelemetryNames.Shooter.status, PKStatus.unknown.tlmValue);
        {
            TurretFactory.constructInstance();
            ISubsystem ss = TurretFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        SmartDashboard.putNumber(TelemetryNames.Shooter.status, PKStatus.unknown.tlmValue);
        {
            ShooterFactory.constructInstance();
            ISubsystem ss = ShooterFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        /***************
         * Control Panel
         ***************/

        SmartDashboard.putNumber(TelemetryNames.Wheel.status, PKStatus.unknown.tlmValue);
        {
            WheelFactory.constructInstance();
            ISubsystem ss = WheelFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        /***************
         * Climber
         ***************/

        SmartDashboard.putNumber(TelemetryNames.Climber.status, PKStatus.unknown.tlmValue);
        {
            ClimberFactory.constructInstance();
            ISubsystem ss = ClimberFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // Load the default commands now that all subsystems are created
        for (ISubsystem ss : subsystems) {
            ss.loadDefaultCommand();
        }

        logger.info("constructed");
        return subsystems;
    }

}
