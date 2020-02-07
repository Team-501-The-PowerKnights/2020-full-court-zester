/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hopper;

import org.slf4j.Logger;

import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.hopper.IHopperSubsystem;
import frc.robot.subsystems.hopper.HopperFactory;

import riolog.RioLogger;

public class HopperSimpleManual extends PKManualCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(HopperSimpleManual.class.getName());

    // Handle to our subsystem
    private IHopperSubsystem hopper;

    public HopperSimpleManual() {
        logger.info("constructing {}", getName());

        hopper = HopperFactory.getInstance();
        addRequirements(hopper);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getHopperSpeed();

        hopper.agitate(speed);
    }

}