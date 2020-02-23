/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.intake.IIntakeSubsystem;
import frc.robot.subsystems.intake.IntakeFactory;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class IntakeCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IntakeCommandBase.class.getName());

    // Handle to our subsystem
    protected IIntakeSubsystem intake;

    public IntakeCommandBase() {
        logger.info("constructing {}", getName());

        intake = IntakeFactory.getInstance();

        addRequirements(intake);

        logger.info("constructed");
    }

}