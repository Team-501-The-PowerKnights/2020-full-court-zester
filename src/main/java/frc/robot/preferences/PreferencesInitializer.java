/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.preferences;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;

import frc.robot.preferences.PreferenceNames.*;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class PreferencesInitializer {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PreferencesInitializer.class.getName());

    /** Handle to the WPILib <code>Preferences</code> **/
    private static final Preferences prefs;

    static {
        // Get handle to WPILib preferences and read file from disk
        prefs = Preferences.getInstance();
    }

    public static void initialize() {
        logger.info("initializing");

        /*
         * Drive
         */

        if (!prefs.containsKey(Drive.pid_P)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_P);
            prefs.putDouble(Drive.pid_P, 0.0);
        }

        if (!prefs.containsKey(Drive.pid_I)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_I);
            prefs.putDouble(Drive.pid_I, 0.0);
        }

        if (!prefs.containsKey(Drive.pid_D)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_D);
            prefs.putDouble(Drive.pid_D, 0.0);
        }

        if (!prefs.containsKey(Drive.pid_F)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_F);
            prefs.putDouble(Drive.pid_F, 0.0);
        }

        /*
         * Turret
         */

        if (!prefs.containsKey(Turret.pid_P)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_P);
            prefs.putDouble(Turret.pid_P, 0.0);
        }

        if (!prefs.containsKey(Turret.pid_I)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_I);
            prefs.putDouble(Turret.pid_I, 0.0);
        }

        if (!prefs.containsKey(Turret.pid_D)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_D);
            prefs.putDouble(Turret.pid_D, 0.0);
        }

        if (!prefs.containsKey(Turret.pid_F)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_F);
            prefs.putDouble(Turret.pid_F, 0.0);
        }

        /*
         * Shooter
         */

        if (!prefs.containsKey(Shooter.pid_P)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_P);
            prefs.putDouble(Shooter.pid_P, 0.0);
        }

        if (!prefs.containsKey(Shooter.pid_I)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_I);
            prefs.putDouble(Shooter.pid_I, 0.0);
        }

        if (!prefs.containsKey(Shooter.pid_D)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_D);
            prefs.putDouble(Shooter.pid_D, 0.0);
        }

        if (!prefs.containsKey(Shooter.pid_F)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_F);
            prefs.putDouble(Shooter.pid_F, 0.0);
        }

        logger.info("preferences as initialized:\n");
        listPreferences();

        logger.info("initialized");
    }

    public static void listPreferences() {
        StringBuilder buf = new StringBuilder();
        buf.append(" preferences:");
        for (String key : prefs.getKeys().stream().collect(Collectors.toCollection(ArrayList::new))) {
            buf.append("\n..."); // logger gobbles up leading spaces
            buf.append(key).append(" = ").append(prefs.getDouble(key, 3171960.));
        }
        logger.info(buf.toString());
    }

}