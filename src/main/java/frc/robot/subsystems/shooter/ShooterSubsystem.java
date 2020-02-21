/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.preferences.PreferencesNames.*;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

class ShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterSubsystem.class.getName());

    /**
     * Shooter constant values
     */

    private static final double VPGearing = 1;
    private static final double beltGearing = 1;
    private static final double countsPerRevolution = 1;
    private static final double shooterP = 0;
    private static final double shooterI = 0;
    private static final double shooterD = 0;
    private static final double shooterF = 0;

    private static final double turretMaxAngle = 270;
    private static final double turretMinAngle = 0;
    private static final double turretP = 0;
    private static final double turretI = 0;
    private static final double turretD = 0;
    private static final double turretF = 0;

    /**
     * Mechanisms and sensors
     */

    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private CANPIDController shooterPID;

    private CANSparkMax turretMotor;
    private CANEncoder turretEncoder;
    private CANPIDController turretPID;

    // private TalonSRX incrementer; // Unused for now

    private DigitalInput limit;

    /**
     * Creates a new ShooterSubsystem.
     */
    public ShooterSubsystem() {
        logger.info("constructing");

        turretMotor = new CANSparkMax(20, MotorType.kBrushless);
        // +CW +, CCW -
        turretEncoder = new CANEncoder(turretMotor);
        turretPID = new CANPIDController(turretMotor);
        turretPID.setP(turretP);
        turretPID.setI(turretI);
        turretPID.setD(turretD);
        turretPID.setFF(turretF);

        leftMotor = new CANSparkMax(21, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        rightMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        // + spin out, - spin in

        // Slaved and inverted
        rightMotor.follow(leftMotor, true);

        shooterPID = new CANPIDController(leftMotor);
        shooterPID.setP(shooterP);
        shooterPID.setI(shooterI);
        shooterPID.setD(shooterD);
        shooterPID.setFF(shooterF);

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
        SmartDashboard.putNumber(TelemetryNames.Shooter.angle, convertTurretCountsToAngle(turretEncoder.getPosition()));
    }

    @Override
    public void stop() {
        shooterPID.setReference(0, ControlType.kVoltage);
        leftMotor.set(0.0);
    }

    @Override
    public void shoot(double dist) {
        // TODO - Trajectory generation for speed
        shooterPID.setReference(0.2 /* generated speed */, ControlType.kVelocity);
    }

    @Override
    public void shoot() {
        // TODO - Trajectory generation from vision data
        shooterPID.setReference(0.2 /* generated speed */, ControlType.kVelocity);
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
            leftMotor.set(0.1);
        }

        if (limit.get()) {
            turretEncoder.setPosition(0);
            leftMotor.set(0.0);
        }
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
        case 21:
            leftMotor.set(idleShooter(speed));
            break;
        case 22:
            rightMotor.set(idleShooter(speed));
            break;
        case 29:
            // Assuming slaved
            leftMotor.set(idleShooter(speed));
            break;
        default:
            break;
        }
    }

    private double idleShooter(double speed) {
        // Dashboard provides scale for shooter speed
        double scale = Preferences.getInstance().getDouble(Shooter.scale, 1.0);
        speed *= scale;

        // Have to be connected to the field to idle
        final DriverStation ds = DriverStation.getInstance();
        if (ds.getMatchNumber() != 0) {
            speed = Math.max(0.20, speed);
        }
        return speed;
    }

}
