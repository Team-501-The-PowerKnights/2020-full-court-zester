/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ClimberSimpleManual extends ClimberOICommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ClimberSimpleManual.class.getName());

    public ClimberSimpleManual() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        // FIXME - Get Climber Command re-implemented
        // int value = oi.getClimberCommand();
        int value = 0;
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
