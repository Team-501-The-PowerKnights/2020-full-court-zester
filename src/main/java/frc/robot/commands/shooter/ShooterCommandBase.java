/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class ShooterCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterCommandBase.class.getName());

    // Handle to our subsystem
    protected IShooterSubsystem shooter;

    public ShooterCommandBase() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();

        addRequirements(shooter);

        logger.info("constructed");
    }

}
