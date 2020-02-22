/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.wheel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import frc.robot.sensors.wheelcolor.IWheelColorSensor;
import frc.robot.sensors.wheelcolor.WheelColorFactory;
import frc.robot.utils.PKColor;

import riolog.RioLogger;

class WheelSubsystem extends BaseWheelSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelSubsystem.class.getName());

    private IWheelColorSensor colorSensor;

    private CANSparkMax motor;

    /**
     * Creates a new WheelSubsystem.
     */
    public WheelSubsystem() {
        logger.info("constructing");

        colorSensor = WheelColorFactory.getInstance();

        motor = new CANSparkMax(61, MotorType.kBrushless);

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
        motor.set(0.0);
    }

    @Override
    public void runToColor(PKColor color) {

        PKColor targetColor = generateColorOffset(color);

        while (colorSensor.getColor() != targetColor) {
            motor.set(0.5);
        }

        if (colorSensor.getColor() == targetColor) {
            motor.set(0.0);
        }
    }

    @Override
    public void runRevolutions(double numRevolutions) {
        PKColor origColor = colorSensor.getColor();

        double count = 0;

        while (count < 10) {
            motor.set(0.5);

            if (colorSensor.getColor() == origColor) {
                count++;
            }
        }

        if (count >= 10) {
            motor.set(0.0);
        }
    }

    @Override
    public void runCounterClockwise() {
        motor.set(0.5);
    }

    @Override
    public void runCounterClockwise(double speed) {
        motor.set(speed);
    }

    @Override
    public void runClockwise() {
        motor.set(-0.5);
    }

    @Override
    public void runClockwise(double speed) {
        motor.set(-speed);
    }

    private PKColor generateColorOffset(PKColor color) {
        return color; // Offset generation will be dependent on wheel placement
    }

}
