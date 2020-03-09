/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class ShooterSetMid extends PKCommandBase {
    // TODO - This should be a real Shooter subsystem command

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterSetMid.class.getName());

    public ShooterSetMid() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        ShooterFactory.getInstance().setTargetRpm(3345); // 3200
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
