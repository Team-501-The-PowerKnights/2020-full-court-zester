/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.ballevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

class BallevatorSubsystem extends BaseBallevatorSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BallevatorSubsystem.class.getName());

    private final TalonSRX motor;

    private final DigitalInput limit;

    // Keep for telemetry
    private double tlmSpeed;

    public BallevatorSubsystem() {
        logger.info("constructing");

        motor = new TalonSRX(51);

        limit = new DigitalInput(0); // TODO - Determine actual channel

        tlmSpeed = 0.0;

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Ballevator.speed, tlmSpeed);
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
        motor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void lift() {
        // TODO - determine actual predefined value to run ballevator at
        setSpeed(-0.7);
    }

    @Override
    public void lower() {
        // TODO - determine actual predefined value to run ballevator at
        setSpeed(0.7);
    }

    @Override
    public boolean isFull() {
        return limit.get();
    }

    private void setSpeed(double speed) {
        tlmSpeed = speed;
        motor.set(ControlMode.PercentOutput, tlmSpeed);
    }

}
