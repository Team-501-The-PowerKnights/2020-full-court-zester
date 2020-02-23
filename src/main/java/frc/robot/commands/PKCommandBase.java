/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.HashSet;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;

import edu.wpi.first.wpilibj2.command.CommandBase;

import riolog.RioLogger;

/**
 * 
 */
public abstract class PKCommandBase extends CommandBase {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(PKCommandBase.class.getName());

    //
    private static final HashSet<PKCommandBase> activeCommands;
    //
    private static PKCommandBase[] activeCommandsList = new PKCommandBase[0];

    static {
        activeCommands = new HashSet<PKCommandBase>();
    }

    private static void add(PKCommandBase c) {
        activeCommands.add(c);
        activeCommandsList = activeCommands.toArray(new PKCommandBase[0]);
    }

    private static void remove(PKCommandBase c) {
        activeCommands.remove(c);
        activeCommandsList = activeCommands.toArray(new PKCommandBase[0]);
    }

    public static PKCommandBase[] getActiveCommands() {
        return activeCommandsList;
    }

    // Handle to the preferences
    protected final Preferences prefs;
    // Flag for whether the first execution has happened
    private boolean executeOnce;

    protected PKCommandBase() {
        logger.info("constructing for {}", getName());

        prefs = Preferences.getInstance();

        logger.info("constructed");
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

            add(this);
        }
    }

    // Called once when either the Command finishes normally, or when it
    // is interrupted/canceled.
    @Override
    public void end(boolean interrupted) {
        logger.debug("ending {} interrupted={}", getName(), interrupted);

        remove(this);
    }

}
