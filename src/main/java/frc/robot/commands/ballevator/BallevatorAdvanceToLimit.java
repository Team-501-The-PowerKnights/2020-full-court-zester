/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballevator;

import org.slf4j.Logger;

import frc.robot.commands.PKCommand;
import frc.robot.subsystems.ballevator.BallevatorFactory;
import frc.robot.subsystems.ballevator.IBallevatorSubsystem;
import riolog.RioLogger;

public class BallevatorAdvanceToLimit extends PKCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BallevatorAdvanceToLimit.class.getName());

    private IBallevatorSubsystem ballevator;

    public BallevatorAdvanceToLimit() {
        logger.info("constructing {}", getName());

        ballevator = BallevatorFactory.getInstance();

        addRequirements(ballevator);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        ballevator.lift();
    }

    @Override
    public boolean isFinished() {
        return ballevator.isFull();
    }

}
