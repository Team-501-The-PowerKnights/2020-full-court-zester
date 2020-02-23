/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class ShooterDoNothing extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterDoNothing.class.getName());

    public ShooterDoNothing() {
        logger.info("constructing {}", getName());

        addRequirements(ShooterFactory.getInstance());

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();
    }

}
