/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.CommandingNames.Shooter;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterDashboardSpeed extends ShooterCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterDashboardSpeed.class.getName());

    public ShooterDashboardSpeed() {
        logger.info("constructing {}", getName());

        SmartDashboard.putNumber(Shooter.speed, 0.0);

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double speed = SmartDashboard.getNumber(Shooter.speed, 0.0);
        // Assumes that speed controllers are following
        // shooter.setSpeed(29, speed);
        shooter.setTargetRpm(speed);
    }

}
