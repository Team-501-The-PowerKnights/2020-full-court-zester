/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.wheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import frc.robot.sensors.wheelcolor.IWheelColorSensor;
import frc.robot.sensors.wheelcolor.WheelColorFactory;
import frc.robot.utils.PKColor;

import riolog.RioLogger;

class WheelSubsystem extends BaseWheelSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelSubsystem.class.getName());

    private IWheelColorSensor colorSensor;

    private TalonSRX motor;

    /**
     * Creates a new WheelSubsystem.
     */
    public WheelSubsystem() {
        logger.info("constructing");

        colorSensor = WheelColorFactory.getInstance();

        motor = new TalonSRX(42);
        motor.configFactoryDefault();

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        // All necessary telemetry sent via WheelColorSensor
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        motor.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public void runToColor(PKColor color) {

        PKColor targetColor = generateColorOffset(color);

        while (colorSensor.getColor() != targetColor) {
            motor.set(ControlMode.PercentOutput, 0.5);
        }

        if (colorSensor.getColor() == targetColor) {
            motor.set(ControlMode.PercentOutput, 0.0);
        }
    }

    @Override
    public void runRevolutions(double numRevolutions) {
        PKColor origColor = colorSensor.getColor();

        double count = 0;

        while (count < 10) {
            motor.set(ControlMode.PercentOutput, 0.5);

            if (colorSensor.getColor() == origColor) {
                count++;
            }
        }

        if (count >= 10) {
            motor.set(ControlMode.PercentOutput, 0.0);
        }
    }

    @Override
    public void runCounterClockwise() {
        motor.set(ControlMode.PercentOutput, 0.00);
    }

    @Override
    public void runCounterClockwise(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public void runClockwise() {
        motor.set(ControlMode.PercentOutput, -0.75);
    }

    @Override
    public void runClockwise(double speed) {
        motor.set(ControlMode.PercentOutput, -speed);
    }

    private PKColor generateColorOffset(PKColor color) {
        return color; // Offset generation will be dependent on wheel placement
    }

}
