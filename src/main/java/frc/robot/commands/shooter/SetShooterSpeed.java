/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
class SetShooterSpeed extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(SetShooterSpeed.class.getName());

    // Handle to Shooter (we need but don't require)
    private final IShooterSubsystem shooter;

    // Set point for RPM
    private final double target;

    public SetShooterSpeed(double target) {
        logger.info("constructing {} with target = {}", getName(), target);

        shooter = ShooterFactory.getInstance();

        this.target = target;

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        shooter.setTargetRpm(target);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
