/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import riolog.RioLogger;

public class ShooterSpinUpFormula extends ShooterCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterSpinUpFormula.class.getName());

    private IVisionSensor vision;
    private double targetRpm;

    public ShooterSpinUpFormula() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double ty = vision.getY();

        targetRpm = (13.5 * (ty * ty)) - (111.3 * ty) + 3352.4;

        shooter.setRpm(targetRpm);
    }

}
