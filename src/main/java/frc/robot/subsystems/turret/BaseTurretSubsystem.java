/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.turret.TurretDoNothing;
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
abstract class BaseTurretSubsystem extends SubsystemBase implements ITurretSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseTurretSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = SubsystemNames.turretName;

    /** Handle to WPILib preferences manager **/
    protected final Preferences prefs;

    /** Turret PID defaults for subystem **/
    protected double pid_P = 0.5;
    protected double pid_I = 0.005;
    protected double pid_D = 1;
    protected double pid_F = 0;

    protected BaseTurretSubsystem() {
        logger.info("constructing");

        prefs = Preferences.getInstance();
        // Load the current preferences
        loadPreferences();

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommand() {
        PKProperties props = PropertiesManager.getInstance().getProperties(myName);
        String myClassName = props.getString("defaultCommandName");
        String myPkgName = TurretDoNothing.class.getPackage().getName();
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
            ourCommand = (Command) new TurretDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Turret.status, PKStatus.degraded.tlmValue);
        }

        setDefaultCommand(ourCommand);
    }

    protected void loadPreferences() {
        double v;

        logger.info("new preferences for {}:", myName);
        v = prefs.getDouble(PreferenceNames.Turret.pid_P, 0.5);
        logger.info("{} = {}", PreferenceNames.Turret.pid_P, v);
        pid_P = v;
        v = prefs.getDouble(PreferenceNames.Turret.pid_I, 0.005);
        logger.info("{} = {}", PreferenceNames.Turret.pid_I, v);
        pid_I = v;
        v = prefs.getDouble(PreferenceNames.Turret.pid_D, 1);
        logger.info("{} = {}", PreferenceNames.Turret.pid_D, v);
        pid_D = v;
        v = prefs.getDouble(PreferenceNames.Turret.pid_F, 0.0);
        logger.info("{} = {}", PreferenceNames.Turret.pid_F, v);
        pid_F = v;
    }

}
