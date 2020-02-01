/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.subsystems.intake.IntakeFactory;

import riolog.RioLogger;

public class IntakeDoNothing extends PKCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IntakeDoNothing.class.getName());

    public IntakeDoNothing() {
        logger.info("constructing {}", getName());

        addRequirements(IntakeFactory.getInstance());

        // FIXME - Kind of hokey; but avoids code sprawl
        IntakeFactory.getInstance().setDefaultCommand(this);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();
    }

}
