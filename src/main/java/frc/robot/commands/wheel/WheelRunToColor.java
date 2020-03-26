/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheel;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.sensors.wheelcolor.IWheelColorSensor;
import frc.robot.sensors.wheelcolor.WheelColorFactory;
import frc.robot.utils.PKColor;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class WheelRunToColor extends WheelCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelRunToColor.class.getName());

    private IWheelColorSensor colorSensor;

    private PKColor targetColor;
    private PKColor offsetColor;
    private PKColor currentColor;

    public WheelRunToColor() {
        logger.info("constructing {}", getName());

        colorSensor = WheelColorFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        String gameData;

        gameData = DriverStation.getInstance().getGameSpecificMessage();

        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
                case 'B':
                    // Blue case code
                    targetColor = PKColor.blueTarget;
                    break;
                case 'G':
                    // Green case code
                    targetColor = PKColor.greenTarget;
                    break;
                case 'R':
                    // Red case code
                    targetColor = PKColor.redTarget;
                    break;
                case 'Y':
                    // Yellow case code
                    targetColor = PKColor.yellowTarget;
                    break;
                default:
                    // This is corrupt data
                    targetColor = PKColor.invalidTarget;
                    break;
            }

            currentColor = colorSensor.getColor();
            offsetColor = generateColorOffset(targetColor);

        }
    }

    @Override
    public void execute() {
        super.execute();

        wheel.runClockwise();
    }

    @Override
    public boolean isFinished() {
        return offsetColor == currentColor || targetColor == PKColor.invalidTarget;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        wheel.stop();
    }

    private PKColor generateColorOffset(PKColor color) {
        return color; // TODO - Offset generation will be dependent on wheel placement
    }

}
