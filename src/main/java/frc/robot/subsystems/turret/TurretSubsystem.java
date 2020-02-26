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
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.slf4j.Logger;

import frc.robot.sensors.turrethome.TurretHomeFactory;
import frc.robot.sensors.limelight.ILimelightSensor;
import frc.robot.sensors.limelight.LimelightFactory;
import frc.robot.sensors.turrethome.ITurretHomeSensor;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

class TurretSubsystem extends BaseTurretSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(TurretSubsystem.class.getName());

    /**
     * Turret constant values
     */

    private static final double VPGearing = 100;
    private static final double beltGearing = 8;

    /**
     * Mechanisms and sensors
     */

    private CANSparkMax turretMotor;
    private CANEncoder turretEncoder;
    private CANPIDController turretPID;

    private ITurretHomeSensor home;
    private ILimelightSensor limelight;

    /**
     * Creates a new TurretSubsystem.
     */
    public TurretSubsystem() {
        logger.info("constructing");

        turretMotor = new CANSparkMax(20, MotorType.kBrushless);
        turretMotor.restoreFactoryDefaults();
        // +CW +, CCW -
        turretMotor.setInverted(true);
        turretEncoder = new CANEncoder(turretMotor);

        turretPID = new CANPIDController(turretMotor);
        turretPID.setIZone(0.25, 1);
        turretPID.setIMaxAccum(1, 1);
        turretPID.setP(pid_P, 1);
        turretPID.setI(pid_I, 1);
        turretPID.setD(pid_D, 1);
        turretPID.setFF(pid_F, 1);
        turretPID.setOutputRange(-1.0, 1.0, 1);
        turretMotor.setSmartCurrentLimit(10);

        home = TurretHomeFactory.getInstance();

        limelight = LimelightFactory.getInstance();
        limelight.disable();

        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, false);

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Turret.angle, getAngle());
        SmartDashboard.putNumber(TelemetryNames.Turret.position, turretEncoder.getPosition());
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

        if (turretPID != null) {
            turretPID.setP(pid_P, 1);
            turretPID.setI(pid_I, 1);
            turretPID.setD(pid_D, 1);
            turretPID.setFF(pid_F, 1);
        }

    }

    @Override
    public void disable() {
    }

    @Override
    public void stop() {
        turretPID.setReference(0, ControlType.kVoltage);
        turretMotor.set(0.0);
    }

    @Override
    public void setTurretAngle(double angle) {
        // if (angle >= turretMaxAngle) {
        // angle = turretMaxAngle;
        // } else if (angle <= turretMinAngle) {
        // angle = turretMinAngle;
        // }

        double targetCounts = convertTurretAngleToCounts(angle);

        turretPID.setReference(targetCounts, ControlType.kPosition, 1);
    }

    @Override
    public void setAngleFromVision() {
        float Kp = -0.75f;
        float min_command = 0.05f;

        double heading_error = limelight.getError();
        double steering_adjust = 0.0f;

        if (heading_error < 0.5) {
            steering_adjust = Kp * heading_error - min_command;
        } else if (heading_error > 0.5) {
            steering_adjust = Kp * heading_error + min_command;
        }

        SmartDashboard.putNumber(TelemetryNames.Turret.visionPIDOutput, steering_adjust);

        turretPID.setReference(steering_adjust, ControlType.kVoltage, 1);
    }

    @Override
    public void home() {
        logger.debug("starting ...");
        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, false);

        turretMotor.setIdleMode(IdleMode.kBrake);

        /*
         * IMPORTANT - The inner loop get() needs to be there!
         */
        // TODO - Figure out why this doesn't work if no inner get()

        logger.debug("gross test");
        while (!(home.get())) {
            logger.debug("sensor = {}", home.get());
            turretMotor.set(0.55);
        }
        turretMotor.set(0.0);
        logger.debug("found set point (gross)");

        logger.debug("back off");
        while ((home.get())) {
            logger.debug("sensor = {}", home.get());
            turretMotor.set(-0.05);
        }
        turretMotor.set(0.0);
        logger.debug("backed off set point");

        logger.debug("fine test");
        while (!(home.get())) {
            logger.debug("sensor = {}", home.get());
            turretMotor.set(0.03);
        }
        turretMotor.set(0.0);
        logger.debug("found set point (fine)");

        turretEncoder.setPosition(55);

        turretMotor.setIdleMode(IdleMode.kCoast);

        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, true);
        logger.debug("... done");
    }

    private double convertTurretCountsToAngle(double counts) {
        double angle = counts / VPGearing / beltGearing * 360 /* 360 degrees per 1 revolution */;

        return angle;
    }

    private double convertTurretAngleToCounts(double angle) {
        double counts = angle / 360 /* 360 degrees per 1 revolution */ * beltGearing * VPGearing;

        return counts;
    }

    private double getAngle() {
        return convertTurretCountsToAngle(turretEncoder.getPosition());
    }

    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
        case 20:
            turretMotor.set(speed);
            break;
        default:
            break;
        }
    }

    @Override
    public void jogCW() {
        setTurretAngle(getAngle() + 5);
    }

    @Override
    public void jogCCW() {
        setTurretAngle(getAngle() - 5);
    }

}
