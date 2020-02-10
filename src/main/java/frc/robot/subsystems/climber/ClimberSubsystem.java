/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

class ClimberSubsystem extends BaseClimberSubsystem {

    private static final Logger logger = RioLogger.getLogger(ClimberSubsystem.class.getName());

    private AnalogInput limitUp;
    private AnalogInput limitDown;

    private CANSparkMax motor;

    public ClimberSubsystem() {
        logger.info("constructing");

        limitUp = new AnalogInput(0);
        limitDown = new AnalogInput(1);

        motor = new CANSparkMax(71, MotorType.kBrushless);

        logger.info("constructed");
    }

    @Override
    public void extend() {
        while (!(limitUp.getValue() == 1)) {
            motor.set(0.3);
        }
    }

    @Override
    public void climb() {
        while (!(limitDown.getValue() == 1)) {
            motor.set(0.3);
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Climber.topLimit, limitUp.getValue() == 1);
        SmartDashboard.putBoolean(TelemetryNames.Climber.bottomLimit, limitDown.getValue() == 1);
    }

    @Override
    public void stop() {
        motor.set(0.0);
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
