/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

// FIXME - Make these Shooter commands
public class RobotSetFar extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(RobotSetFar.class.getName());

    private IShooterSubsystem shooter;

    public RobotSetFar() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        shooter.setTargetRpm(3395); // 3295
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
