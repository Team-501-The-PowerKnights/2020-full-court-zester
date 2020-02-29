/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import org.slf4j.Logger;

import frc.robot.OI;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ClimberRetractInPit extends ClimberOICommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ClimberRetractInPit.class.getName());

    public ClimberRetractInPit() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        // TODO - Can we just call end from here?
        if (OI.getInstance().isFieldConnected()) {
            return;
        }

        if (OI.getInstance().getClimberRetractEnable()) {
            climber.retract();
        } else {
            climber.stop();
        }
    }

    // Called repeatedly after execute to determine if command is finished
    @Override
    public boolean isFinished() {
        return OI.getInstance().isFieldConnected();
    }

    // Called once when either the Command finishes normally, or when it
    // is interrupted/canceled.
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        climber.stop();
    }

}
