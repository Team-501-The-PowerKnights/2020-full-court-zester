/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;

import edu.wpi.first.wpilibj2.command.CommandBase;

import riolog.RioLogger;

/**
 * 
 */
public abstract class PKCommand extends CommandBase {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(PKCommand.class.getName());

    // Handle to the preferences
    protected final Preferences prefs;
    // Flag for whether the first execution has happened
    private boolean executeOnce;

    protected PKCommand() {
        logger.info("constructing {}", getName());

        prefs = Preferences.getInstance();
    }

    // Called once just before this Command runs the first time
    @Override
    public void initialize() {
        logger.debug("initializing {}", getName());

        executeOnce = false;
    }

    // Called repeatedly while the Command is scheduled
    @Override
    public void execute() {
        logExecuteStart(logger);
    }

    protected void logExecuteStart(Logger logger) {
        if (!executeOnce) {
            executeOnce = true;
            logger.trace("first execution of {}", getName());
        }
    }

    // Called once when either the Command finishes normally, or when it
    // is interrupted/canceled.
    @Override
    public void end(boolean interrupted) {
        logger.debug("ending {} interrupted={}", getName(), interrupted);
    }

}
