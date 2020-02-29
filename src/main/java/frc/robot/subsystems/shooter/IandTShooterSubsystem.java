/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import riolog.RioLogger;

class IandTShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IandTShooterSubsystem.class.getName());

    /**
     * Mechanisms and sensors
     */

    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private CANPIDController shooterPID;

    /**
     * Creates a new ShooterSubsystem.
     */
    public IandTShooterSubsystem() {
        logger.info("constructing");

        leftMotor = new CANSparkMax(21, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        rightMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        // + spin out, - spin in

        rightMotor.setInverted(true);

        // rightMotor.follow(leftMotor);

        shooterPID = new CANPIDController(leftMotor);
        shooterPID.setP(pid_P);
        shooterPID.setI(pid_I);
        shooterPID.setD(pid_D);
        shooterPID.setFF(pid_F);

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        loadPreferences();

        shooterPID.setP(pid_P);
        shooterPID.setI(pid_I);
        shooterPID.setD(pid_D);
        shooterPID.setFF(pid_F);
    }

    @Override
    public void disable() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    @Override
    public void stop() {
        shooterPID.setReference(0, ControlType.kVoltage);
        leftMotor.set(0.0);
    }

    @Override
    public void setTargetRpm(double rpm) {
        // TODO - Trajectory generation for speed
        shooterPID.setReference(0.2 /* generated speed */, ControlType.kVelocity);
    }

    @Override
    public void shoot() {
        // TODO - Trajectory generation from vision data
        shooterPID.setReference(0.2 /* generated speed */, ControlType.kVelocity);
    }

    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
            case 21:
                // motor.set(speed);
                // motor.set(0.1);
                break;
            case 22:
                rightMotor.set(speed);
                // shootSlave0.set(0.1);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean atTargetVelocity() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getActivePosition() {
        // TODO Auto-generated method stub
        return null;
    }

}
