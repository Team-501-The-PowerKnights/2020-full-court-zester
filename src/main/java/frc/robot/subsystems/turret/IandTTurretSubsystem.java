/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

class IandTTurretSubsystem extends BaseTurretSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IandTTurretSubsystem.class.getName());

    /**
     * Turret constant values
     */

    private static final double VPGearing = 1;
    private static final double beltGearing = 1;
    private static final double countsPerRevolution = 1;

    /**
     * Mechanisms and sensors
     */

    private CANSparkMax turretMotor;
    private CANEncoder turretEncoder;
    private CANPIDController turretPID;

    // private TalonSRX incrementer; // Unused for now

    private DigitalInput limit;

    /**
     * Creates a new TurretSubsystem.
     */
    public IandTTurretSubsystem() {
        logger.info("constructing");

        turretMotor = new CANSparkMax(20, MotorType.kBrushless);
        turretMotor.restoreFactoryDefaults();
        // +CW +, CCW -
        turretEncoder = new CANEncoder(turretMotor);

        turretPID = new CANPIDController(turretMotor);
        turretPID.setP(pid_P);
        turretPID.setI(pid_I);
        turretPID.setD(pid_D);
        turretPID.setFF(pid_F);

        // incrementer = new TalonSRX(23); // Unused for now

        limit = new DigitalInput(0);

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Turret.angle, convertTurretCountsToAngle(turretEncoder.getPosition()));
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        loadPreferences();

        turretPID.setP(pid_P);
        turretPID.setI(pid_I);
        turretPID.setD(pid_D);
        turretPID.setFF(pid_F);

    }

    @Override
    public void disable() {
        turretMotor.set(0);
    }

    @Override
    public void stop() {
        turretPID.setReference(0, ControlType.kVoltage);
    }

    @Override
    public void setTurretAngle(double angle) {
        // if (angle >= turretMaxAngle) {
        // angle = turretMaxAngle;
        // } else if (angle <= turretMinAngle) {
        // angle = turretMinAngle;
        // }

        // double targetCounts = convertTurretAngleToCounts(angle);

        // turretPID.setReference(targetCounts, ControlType.kPosition);
        turretMotor.set(angle);
    }

    @Override
    public void home() {
        while (!(limit.get())) {
            turretMotor.set(0.1);
        }

        if (limit.get()) {
            turretEncoder.setPosition(0);
            turretMotor.set(0.0);
        }
    }

    private double convertTurretCountsToAngle(double counts) {
        double angle = (counts / countsPerRevolution) * VPGearing * beltGearing
                * 360 /* 360 degrees per 1 revolution */;

        return angle;
    }

    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
            case 20:
                turretMotor.set(speed);
                break;
        }
    }

    @Override
    public void jogCW() {
        // TODO Auto-generated method stub

    }

    @Override
    public void jogCCW() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setAngleFromVision() {
        // TODO Auto-generated method stub

    }

    @Override
    public void holdAngle() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isAtAngle(double targetAngle) {
        // TODO Auto-generated method stub
        return false;
    }

}
