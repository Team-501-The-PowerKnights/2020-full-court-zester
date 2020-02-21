/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import org.slf4j.Logger;

import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.climber.IClimberSubsystem;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ClimberSimpleManual extends PKManualCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ClimberSimpleManual.class.getName());

    // Handle to our subsystem
    private IClimberSubsystem climber;

    public ClimberSimpleManual() {
        logger.info("constructing {}", getName());

        climber = ClimberFactory.getInstance();
        addRequirements(climber);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        int value = oi.getClimberCommand();
        switch (value) {
        case 0:
            climber.stop();
            break;
        case 1:
            climber.extend();
            break;
        case 2:
            climber.retract();
            break;
        default:
            climber.stop();
            break;
        }
    }

}
