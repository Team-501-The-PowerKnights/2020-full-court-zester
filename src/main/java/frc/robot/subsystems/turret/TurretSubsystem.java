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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.slf4j.Logger;

import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.sensors.turretlocation.ITurretLocationSensor;
import frc.robot.sensors.turretlocation.TurretLocationFactory;
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

    private final CANSparkMax motor;
    private final CANEncoder encoder;
    private final CANPIDController pid;

    private final ITurretLocationSensor location;

    private final IVisionSensor vision;

    private double targetAngle;

    /**
     * Creates a new TurretSubsystem.
     */
    public TurretSubsystem() {
        logger.info("constructing");

        motor = new CANSparkMax(20, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        // +CW +, CCW -
        motor.setInverted(true);
        encoder = new CANEncoder(motor);

        pid = new CANPIDController(motor);
        pid.setIZone(0.25, 1);
        pid.setIMaxAccum(1, 1);
        pid.setP(pid_P, 1);
        pid.setI(pid_I, 1);
        pid.setD(pid_D, 1);
        pid.setFF(pid_F, 1);
        pid.setOutputRange(-1.0, 1.0, 1);
        motor.setSmartCurrentLimit(10);

        location = TurretLocationFactory.getInstance();

        vision = VisionFactory.getInstance();
        vision.disable();

        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, false);

        encoder.setPosition(convertTurretAngleToCounts(-90));

        targetAngle = 0;

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    // TODO - Probably should be in Base with interface methods
    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Turret.angle, getAngle());
        SmartDashboard.putNumber(TelemetryNames.Turret.position, encoder.getPosition());
    }

    @Override
    public void updatePreferences() {
        loadPreferences();

        if (pid != null) {
            pid.setP(pid_P, 1);
            pid.setI(pid_I, 1);
            pid.setD(pid_D, 1);
            pid.setFF(pid_F, 1);
        }
    }

    @Override
    public void stop() {
        pid.setReference(0, ControlType.kVoltage);
        motor.set(0.0);
    }

    @Override
    public void setTurretAngle(double angle) {
        // if (angle >= turretMaxAngle) {
        // angle = turretMaxAngle;
        // } else if (angle <= turretMinAngle) {
        // angle = turretMinAngle;
        // }

        targetAngle = angle;

        double targetCounts = convertTurretAngleToCounts(angle);

        pid.setReference(targetCounts, ControlType.kPosition, 1);
    }

    @Override
    public void setAngleFromVision() {
        float Kp = -0.75f;
        float min_command = 0.05f;

        double heading_error = vision.getError();
        double steering_adjust = 0.0f;

        if (heading_error < 0.5) {
            steering_adjust = Kp * heading_error - min_command;
        } else if (heading_error > 0.5) {
            steering_adjust = Kp * heading_error + min_command;
        }

        SmartDashboard.putNumber(TelemetryNames.Turret.visionPIDOutput, steering_adjust);

        pid.setReference(steering_adjust, ControlType.kVoltage, 1);
    }

    // FIXME - So not done with Thread.sleep()
    @Override
    public void home() {
        logger.debug("starting ...");
        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, false);

        if (DriverStation.getInstance().isAutonomous()) {
            encoder.setPosition(convertTurretAngleToCounts(-90));
        } else if (DriverStation.getInstance().isOperatorControl()) {
            double firstAngle = getAngle();

            /*
             * IMPORTANT - The inner loop get() needs to be there!
             */
            // TODO - Figure out why this doesn't work if no inner get()

            logger.debug("gross test");
            while (!(location.get())) {
                logger.debug("sensor = {}", location.get());
                motor.set(0.55);
                if (getAngle() - firstAngle >= 100) {
                    return;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // Just ignore it
                }
            }
            motor.set(0.0);
            logger.debug("found set point (gross)");

            logger.debug("back off");
            while ((location.get())) {
                logger.debug("sensor = {}", location.get());
                motor.set(-0.05);
                if (getAngle() - firstAngle >= 100) {
                    return;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // Just ignore it
                }
            }
            motor.set(0.0);
            logger.debug("backed off set point");

            logger.debug("fine test");
            while (!(location.get())) {
                logger.debug("sensor = {}", location.get());
                motor.set(0.03);
                if (getAngle() - firstAngle >= 100) {
                    return;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // Just ignore it
                }
            }
            motor.set(0.0);
            logger.debug("found set point (fine)");

            encoder.setPosition(55);

            motor.setIdleMode(IdleMode.kCoast);
        }

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
        return convertTurretCountsToAngle(encoder.getPosition());
    }

    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
        case 20:
            motor.set(speed);
            break;
        default:
            break;
        }
    }

    @Override
    public void jogCW() {
        setTurretAngle(getAngle() + 2.5);
    }

    @Override
    public void jogCCW() {
        setTurretAngle(getAngle() - 2.5);
    }

    @Override
    public void holdAngle() {
        double currentAngle = getAngle();

        setTurretAngle(currentAngle);
    }

    @Override
    public boolean isAtAngle(double targetAngle) {
        return getAngle() >= targetAngle;
    }

    @Override
    public void fineTuneTargetAngle(double axisVal) {
        double initTargetAngle = targetAngle;
        double adjustment = axisVal * (0.1 * targetAngle);
        if (targetAngle == initTargetAngle)
            targetAngle += adjustment;
    }

}
