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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.wheelcolor.IWheelColorSensor;
import frc.robot.sensors.wheelcolor.WheelColorFactory;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKColor;
import riolog.RioLogger;

public class WheelSubsystem extends BaseWheelSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(WheelSubsystem.class.getName());

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Wheel.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new WheelSubsystem();

        SmartDashboard.putBoolean(TelemetryNames.Wheel.status, true);
    }

    public static IWheelSubsystem getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    private IWheelColorSensor colorSensor;

    private CANSparkMax motor;

    /**
     * Creates a new WheelSubsystem.
     */
    public WheelSubsystem() {
        logger.info("constructing");

        colorSensor = WheelColorFactory.getInstance();

        motor = new CANSparkMax(0, MotorType.kBrushless);

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void runToColor(PKColor color) {
        // TODO Auto-generated method stub

    }

    @Override
    public void runRevolutions(double numRevolutions) {
        // TODO Auto-generated method stub

    }

    @Override
    public void runCounterClockwise() {
        // TODO Auto-generated method stub

    }

    @Override
    public void runCounterClockwise(double speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void runClockwise() {
        // TODO Auto-generated method stub

    }

    @Override
    public void runClockwise(double speed) {
        // TODO Auto-generated method stub

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

}
