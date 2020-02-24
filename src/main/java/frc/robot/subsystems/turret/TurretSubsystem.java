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

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    private DigitalInput home;

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

        // TODO - This should be sensor so it can telemetry
        home = new DigitalInput(8);

        disableLimelight();

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

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

        // post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", table.getEntry("tx").getDouble(0.0));
        SmartDashboard.putNumber("LimelightY", table.getEntry("ty").getDouble(0.0));
        SmartDashboard.putNumber("LimelightArea", table.getEntry("ta").getDouble(0.0));
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
        disableLimelight();
    }

    @Override
    public void stop() {
        disableLimelight();
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

    final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    @Override
    public void setAngleFromVision() {

        double x = table.getEntry("tx").getDouble(0.0);

        SmartDashboard.putNumber("Heading Error", x);

        float Kp = -0.75f;
        float min_command = 0.05f;

        double heading_error = -x;
        double steering_adjust = 0.0f;

        if (x > 0.5) {
            steering_adjust = Kp * heading_error - min_command;
        } else if (x < 0.5) {
            steering_adjust = Kp * heading_error + min_command;
        }

        SmartDashboard.putNumber("Output", steering_adjust);

        turretPID.setReference(steering_adjust, ControlType.kVoltage, 1);
    }

    @Override
    public void enableLimelight() {
        table.getEntry("ledMode").setDouble(3);
        table.getEntry("camMode").setDouble(0);
    }

    @Override
    public void disableLimelight() {
        table.getEntry("ledMode").setDouble(1);
        table.getEntry("camMode").setDouble(1);
    }

    @Override
    public void home() {

        SmartDashboard.putBoolean(TelemetryNames.Turret.isHome, false);

        turretMotor.setIdleMode(IdleMode.kBrake);

        while (!(home.get())) {
            turretMotor.set(0.55);
        }

        if (home.get()) {
            turretMotor.set(0.0);
            turretEncoder.setPosition(0);
        }

        while ((home.get())) {
            turretMotor.set(-0.05);
        }

        if (home.get()) {
            turretMotor.set(0.0);
        }

        while (!(home.get())) {
            turretMotor.set(0.03);
        }

        if (home.get()) {
            turretMotor.set(0.0);
            turretEncoder.setPosition(55);
        }

        turretMotor.setIdleMode(IdleMode.kCoast);

        SmartDashboard.putBoolean(TelemetryNames.Turret.isHome, true);

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
