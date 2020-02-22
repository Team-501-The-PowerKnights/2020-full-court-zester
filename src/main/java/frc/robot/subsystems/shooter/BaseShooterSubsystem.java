/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.preferences.PreferenceNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class BaseShooterSubsystem extends SubsystemBase implements IShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseShooterSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = TelemetryNames.Shooter.name;

    /** Handle to WPILib preferences manager **/
    protected final Preferences prefs;

    /** Turret PID defaults for subystem **/
    protected double turretP = 0;
    protected double turretI = 0;
    protected double turretD = 0;
    protected double turretF = 0;
    /** Shooter PID defaults for subystem **/
    protected double shooterP = 0;
    protected double shooterI = 0;
    protected double shooterD = 0;
    protected double shooterF = 0;

    protected BaseShooterSubsystem() {
        logger.info("constructing");

        prefs = Preferences.getInstance();
        // Load the current preferences
        updatePreferences();

        logger.info("constructed");
    }

    @Override
    public void updatePreferences() {
        double v;

        logger.info("new preferences for {}:", myName);
        v = prefs.getDouble(PreferenceNames.Turret.pid_P, 0.0);
        logger.info("{} = {}", PreferenceNames.Turret.pid_P, v);
        turretP = v;
        v = prefs.getDouble(PreferenceNames.Turret.pid_I, 0.0);
        logger.info("{} = {}", PreferenceNames.Turret.pid_I, v);
        turretI = v;
        v = prefs.getDouble(PreferenceNames.Turret.pid_D, 0.0);
        logger.info("{} = {}", PreferenceNames.Turret.pid_D, v);
        turretD = v;
        v = prefs.getDouble(PreferenceNames.Turret.pid_F, 0.0);
        logger.info("{} = {}", PreferenceNames.Turret.pid_F, v);
        turretF = v;

        logger.info("new preferences for {}:", myName);
        v = prefs.getDouble(PreferenceNames.Shooter.pid_P, 0.0);
        logger.info("{} = {}", PreferenceNames.Shooter.pid_P, v);
        shooterP = v;
        v = prefs.getDouble(PreferenceNames.Shooter.pid_I, 0.0);
        logger.info("{} = {}", PreferenceNames.Shooter.pid_I, v);
        shooterI = v;
        v = prefs.getDouble(PreferenceNames.Shooter.pid_D, 0.0);
        logger.info("{} = {}", PreferenceNames.Shooter.pid_D, v);
        shooterD = v;
        v = prefs.getDouble(PreferenceNames.Shooter.pid_F, 0.0);
        logger.info("{} = {}", PreferenceNames.Shooter.pid_F, v);
        shooterF = v;
    }

}
