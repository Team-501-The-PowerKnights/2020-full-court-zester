/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterHome extends ShooterCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterManual.class.getName());

    public ShooterHome() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called once just before this Command runs the first time
    @Override
    public void initialize() {
        // TODO - Make this one of the execute once commands? Do we need to wait?
        shooter.home();
    }

}
