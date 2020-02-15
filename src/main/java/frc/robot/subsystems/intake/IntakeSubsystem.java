/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

public class IntakeSubsystem extends BaseIntakeSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IntakeSubsystem.class.getName());

    private final TalonSRX ingestMotor;

    // Keep for telemetry
    private double tlmSpeed;

    private PIDController pid;
    private double pidP;
    private double pidI;
    private double pidD;

    /**
     * Creates a new IntakeSubsystem.
     */
    public IntakeSubsystem() {
        logger.info("constructing");

        ingestMotor = new TalonSRX(41);

        pid = new PIDController(0.0, 0.0, 0.0);
        pidP = 0.0;
        pidI = 0.0;
        pidD = 0.0;
        pid.setP(pidP);
        pid.setI(pidI);
        pid.setD(pidD);

        tlmSpeed = 0.0;

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Intake.speed, tlmSpeed);
    }

    @Override
    public void stop() {
        setSpeed(0.0);
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        // TODO Get PID values from preferences

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pullIn() {
        // TODO - determine actual preset speed values
        setSpeed(0.2);
    }

    @Override
    public void pullIn(double speed) {
        setSpeed(speed);
    }

    @Override
    public void pushOut() {
        // TODO - determine actual preset speed values
        setSpeed(-0.2);
    }

    @Override
    public void pushOut(double speed) {
        setSpeed(-speed);
    }

    private void setSpeed(double speed) {
        tlmSpeed = speed;
        ingestMotor.set(ControlMode.PercentOutput, tlmSpeed);
    }

    @Override
    public void raise() {
        // agitatorMotor.set(ControlMode.Position, 0.0); // TODO - determine actual
        // position
    }

    @Override
    public void lower() {
        // agitatorMotor.set(ControlMode.Position, 25.0); // TODO - determine actual
        // position
    }

}