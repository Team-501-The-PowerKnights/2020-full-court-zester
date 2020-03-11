/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheel;

import org.slf4j.Logger;

import frc.robot.sensors.wheelcolor.IWheelColorSensor;
import frc.robot.sensors.wheelcolor.WheelColorFactory;
import frc.robot.utils.PKColor;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class WheelRunRevolutions extends WheelCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelRunRevolutions.class.getName());

    private IWheelColorSensor colorSensor;

    private PKColor targetColor;
    private PKColor lastColor;

    private double count;

    public WheelRunRevolutions() {
        logger.info("constructing {}", getName());

        colorSensor = WheelColorFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        targetColor = colorSensor.getColor();
        lastColor = PKColor.invalidTarget;

        count = 0;
    }

    @Override
    public void execute() {
        super.execute();

        PKColor currentColor = colorSensor.getColor();

        if (count < 10) {

            wheel.runClockwise();

            if (currentColor == targetColor && currentColor != lastColor) {
                count++;
            }

        }

        lastColor = currentColor;
    }

    @Override
    public boolean isFinished() {
        return count >= 10;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        wheel.stop();
    }

}
