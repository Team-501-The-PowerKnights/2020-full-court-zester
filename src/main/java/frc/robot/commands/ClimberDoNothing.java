/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.subsystems.climber.ClimberFactory;

import riolog.RioLogger;

public class ClimberDoNothing extends PKCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ClimberDoNothing.class.getName());

    public ClimberDoNothing() {
        logger.info("constructing {}", getName());

        addRequirements(ClimberFactory.getInstance());

        // FIXME - Kind of hokey; but avoids code sprawl
        ClimberFactory.getInstance().setDefaultCommand(this);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();
    }

}
