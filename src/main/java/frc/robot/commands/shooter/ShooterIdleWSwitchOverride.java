/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.OI;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterIdleWSwitchOverride extends ShooterIdle {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterIdleWSwitchOverride.class.getName());

    private final OI oi;

    public ShooterIdleWSwitchOverride() {
        logger.info("constructing {}", getName());

        oi = OI.getInstance();

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        if ( oi.isShooterRevEnabled())
        {
            new ShooterEnableSpin().schedule();
        }
    }

}
