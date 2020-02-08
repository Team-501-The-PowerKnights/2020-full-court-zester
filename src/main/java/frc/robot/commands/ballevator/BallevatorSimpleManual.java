/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballevator;

import org.slf4j.Logger;

import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.ballevator.IBallevatorSubsystem;
import frc.robot.subsystems.ballevator.BallevatorFactory;

import riolog.RioLogger;

public class BallevatorSimpleManual extends PKManualCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BallevatorSimpleManual.class.getName());

    // Handle to our subsystem
    private IBallevatorSubsystem ballevator;

    public BallevatorSimpleManual() {
        logger.info("constructing {}", getName());

        ballevator = BallevatorFactory.getInstance();
        addRequirements(ballevator);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getBallevatorSpeed();

        if (speed > 0.05) {
            ballevator.lift();
            // FIXME - Should this be negative?
        } else if (speed < 0.05) {
            ballevator.lower();
        } else {
            ballevator.stop();
        }
    }

}