/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class PKSequentialCommandGroup extends SequentialCommandGroup {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(PKSequentialCommandGroup.class.getName());

    // FIXME - Use handle to CommandTracker class (TBW)
    // FIXME - Commands are handled differently (not through scheduler)

    // Handle to the preferences
    protected final Preferences prefs;

    // Flag for whether the first execution has happened
    private boolean executeOnce;

    public PKSequentialCommandGroup() {
        logger.info("constructing for {}", getName());

        prefs = Preferences.getInstance();

        logger.info("constructed");
    }

    public PKSequentialCommandGroup(Command... commands) {
        super(commands);
        logger.info("constructing for {}", getName());

        prefs = Preferences.getInstance();

        logger.info("constructed");
    }

    // FIXME - addCommands() is final in base class

    // Called once just before this Command runs the first time
    @Override
    public void initialize() {
        logger.debug("initializing {}", getName());

        executeOnce = false;

        super.initialize();
    }

    // Called repeatedly while the Command is scheduled
    @Override
    public void execute() {
        logExecuteStart(logger);

        super.execute();
    }

    protected void logExecuteStart(Logger logger) {
        if (!executeOnce) {
            executeOnce = true;
            logger.trace("first execution of {}", getName());

            // add(this);
        }
    }

    // Called once when either the Command finishes normally, or when it
    // is interrupted/canceled.
    @Override
    public void end(boolean interrupted) {
        logger.debug("ending {} interrupted={}", getName(), interrupted);

        // remove(this);

        super.end(interrupted);
    }

}
