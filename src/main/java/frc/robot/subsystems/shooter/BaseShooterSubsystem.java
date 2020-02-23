/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.shooter.ShooterDoNothing;
import frc.robot.preferences.PreferenceNames;
import frc.robot.properties.PKProperties;
import frc.robot.properties.PropertiesManager;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class BaseShooterSubsystem extends SubsystemBase implements IShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseShooterSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = SubsystemNames.shooterName;

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
        // load the current preferences
        loadPreferences();

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommand() {
        PKProperties props = PropertiesManager.getInstance().getProperties(myName);
        String myClassName = props.getString("defaultCommandName");
        String myPkgName = ShooterDoNothing.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem", myClassName, myName);
        Command ourCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourCommand = (Command) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourCommand = (Command) new ShooterDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Shooter.status, PKStatus.degraded.tlmValue);
        }

        setDefaultCommand(ourCommand);
    }

    protected void loadPreferences() {
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
