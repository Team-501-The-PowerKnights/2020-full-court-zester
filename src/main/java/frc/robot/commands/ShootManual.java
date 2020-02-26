/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.commands.shooter.ShooterSimpleManual;
import frc.robot.commands.turret.TurretSimpleManual;
import riolog.RioLogger;

/**
 * 
 */
public class ShootManual extends PKParallelCommandGroup {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(ShootManual.class.getName());

    public ShootManual() {
        super(new TurretSimpleManual(), new ShooterSimpleManual());
        logger.info("constructing for {}", getName());

        logger.info("constructed");
    }

}
