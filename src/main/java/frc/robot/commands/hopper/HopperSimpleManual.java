/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hopper;

import org.slf4j.Logger;

import riolog.RioLogger;

public class HopperSimpleManual extends HopperOICommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(HopperSimpleManual.class.getName());

    public HopperSimpleManual() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        boolean active = oi.getHopperActive();

        if (active) {
            hopper.agitate();
        } else {
            hopper.stop();
        }

    }

}