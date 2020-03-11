/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.turret.ITurretSubsystem;
import frc.robot.subsystems.turret.TurretFactory;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
class SetTurretPosition extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(SetTurretPosition.class.getName());

    // Handle to Turret (we need but don't require)
    private final ITurretSubsystem turret;

    // Set point for position
    private final double target;

    public SetTurretPosition(double target) {
        logger.info("constructing {} with target = {}", getName(), target);

        turret = TurretFactory.getInstance();

        this.target = target;

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        turret.setTargetAngle(target);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}