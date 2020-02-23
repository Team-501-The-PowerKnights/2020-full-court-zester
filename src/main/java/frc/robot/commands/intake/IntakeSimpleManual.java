/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import org.slf4j.Logger;

import riolog.RioLogger;

public class IntakeSimpleManual extends IntakeOICommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IntakeSimpleManual.class.getName());

    public IntakeSimpleManual() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double speed = oi.getIntakeSpeed();

        intake.pullIn(speed);
    }

}
