/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterManual extends ShooterOICommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterManual.class.getName());

    public ShooterManual() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getShooterSpeed();
        shooter.setSpeed(29, speed);

        speed = oi.getTurretSpeed();
        shooter.setSpeed(20, speed);
    }

}
