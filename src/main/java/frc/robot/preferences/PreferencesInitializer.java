/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.preferences;

import java.util.Collection;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;

import frc.robot.preferences.PreferencesNames.*;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class PreferencesInitializer {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PreferencesInitializer.class.getName());

    private static final Preferences prefs = Preferences.getInstance();

    public static void initialize() {
        logger.info("Initializing");

        /*
         * Drive
         */

        if (!prefs.containsKey(Drive.p)) {
            logger.warn("{} doesn't exist; creating with default", Drive.p);
            prefs.putDouble(Drive.p, 0.0);
        }

        if (!prefs.containsKey(Drive.i)) {
            logger.warn("{} doesn't exist; creating with default", Drive.i);
            prefs.putDouble(Drive.i, 0.0);
        }

        if (!prefs.containsKey(Drive.d)) {
            logger.warn("{} doesn't exist; creating with default", Drive.d);
            prefs.putDouble(Drive.d, 0.0);
        }

        if (!prefs.containsKey(Drive.f)) {
            logger.warn("{} doesn't exist; creating with default", Drive.f);
            prefs.putDouble(Drive.f, 0.0);
        }

        /*
         * Shooter
         */

        if (!prefs.containsKey(Shooter.p)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.p);
            prefs.putDouble(Shooter.p, 0.0);
        }

        if (!prefs.containsKey(Shooter.i)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.i);
            prefs.putDouble(Shooter.i, 0.0);
        }

        if (!prefs.containsKey(Shooter.d)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.d);
            prefs.putDouble(Shooter.d, 0.0);
        }

        if (!prefs.containsKey(Shooter.f)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.f);
            prefs.putDouble(Shooter.f, 0.0);
        }

        if (!prefs.containsKey(Shooter.scale)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.scale);
            prefs.putDouble(Shooter.scale, 1.0);
        }

        Collection<String> keys = prefs.getKeys();
        logger.info("preferences as initialized:\n");

        logger.info("Initialized");
    }

}
