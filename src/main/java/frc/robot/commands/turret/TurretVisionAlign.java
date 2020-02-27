/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;

import riolog.RioLogger;

public class TurretVisionAlign extends TurretCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(TurretVisionAlign.class.getName());

    private IVisionSensor vision;

    public TurretVisionAlign() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        vision.enable();
    }

    @Override
    public void execute() {
        super.execute();

        turret.setAngleFromVision();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        vision.disable();
        turret.holdAngle();
    }

}
