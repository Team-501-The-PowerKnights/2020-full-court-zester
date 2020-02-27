/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.subsystems.hopper.HopperFactory;
import frc.robot.subsystems.hopper.IHopperSubsystem;
import frc.robot.subsystems.intake.IIntakeSubsystem;
import frc.robot.subsystems.intake.IntakeFactory;

import riolog.RioLogger;

public class IngestPose extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IngestPose.class.getName());

    private IIntakeSubsystem intake;
    private IHopperSubsystem hopper;

    public IngestPose() {
        logger.info("constructing {}", getName());

        intake = IntakeFactory.getInstance();
        hopper = HopperFactory.getInstance();

        addRequirements(intake, hopper);

        logger.info("constructed {}", getName());
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        intake.pullIn();
        hopper.agitate();
    }

    @Override
    public void end(boolean interrupted) {
        hopper.stop();
        intake.stop();

        super.end(interrupted);
    }
}
