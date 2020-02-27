/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.hopper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

class HopperSubsystem extends BaseHopperSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(HopperSubsystem.class.getName());

    // Our motor to do agitation (no directionality required)
    private TalonSRX motor;

    // Keep for telemetry
    private double tlmSpeed;

    public HopperSubsystem() {
        logger.info("constructing");

        motor = new TalonSRX(31);
        motor.configFactoryDefault();

        tlmSpeed = 0.0;

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Hopper.speed, tlmSpeed);
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
        setSpeed(0.0);
    }

    @Override
    public void agitate() {
        // TODO - Determine actual preset speed
        setSpeed(-0.30);
    }

    @Override
    public void agitate(double speed) {
        setSpeed(speed);
    }

    private void setSpeed(double speed) {
        tlmSpeed = speed;
        motor.set(ControlMode.PercentOutput, tlmSpeed);
    }

}
