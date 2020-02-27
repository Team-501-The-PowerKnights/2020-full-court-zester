/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import riolog.RioLogger;

public class AutoTurretPositionBack extends TurretCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(AutoTurretPositionBack.class.getName());

    public AutoTurretPositionBack() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();

        turret.setTurretAngle(-10);
    }

    @Override
    public boolean isFinished() {
        return turret.isAtAngle(-10);
    }

}
