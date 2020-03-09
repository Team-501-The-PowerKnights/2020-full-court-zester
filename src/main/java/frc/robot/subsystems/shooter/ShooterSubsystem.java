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
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.CommandingNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

public class ShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterSubsystem.class.getName());

    //
    private static final int slotID = 1;

    // Left motor (master)
    private CANSparkMax leftMotor;
    // Right motor
    private CANSparkMax rightMotor;

    // Encoder
    private CANEncoder leftEncoder;
    // PID
    private CANPIDController leftPid;

    // Encoder
    private CANEncoder rightEncoder;
    // PID
    private CANPIDController rightPid;

    // Value of the RPM to use for speed
    private double targetRpm;
    // Value to compare RPM actual to target
    private double tolerance;

    // Flag for whether active (i.e., spinning)
    private boolean isActive;

    /**
     * Creates a new ShooterSubsystem.
     */
    public ShooterSubsystem() {
        logger.info("constructing");

        leftMotor = new CANSparkMax(21, MotorType.kBrushless);
        // + spin out, - spin in
        leftMotor.restoreFactoryDefaults();
        leftMotor.setIdleMode(IdleMode.kBrake);

        leftEncoder = new CANEncoder(leftMotor);

        leftPid = new CANPIDController(leftMotor);
        leftPid.setOutputRange(0, 1, slotID);

        rightMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        rightMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setInverted(true);

        rightEncoder = new CANEncoder(rightMotor);

        rightPid = new CANPIDController(rightMotor);
        rightPid.setOutputRange(0, 1, slotID);

        updatePreferences();

        targetRpm = 3295; // TODO - Make the values
        isActive = false;

        SmartDashboard.putNumber(CommandingNames.Shooter.tolerance, 0.015);

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Shooter.isActive, isActive);
        SmartDashboard.putNumber(TelemetryNames.Shooter.rpm, leftEncoder.getVelocity());
        SmartDashboard.putNumber(TelemetryNames.Shooter.targetRpm, targetRpm);
        SmartDashboard.putBoolean(TelemetryNames.Shooter.atTarget, atTargetVelocity());

        // TODO - Optimize this / put in I&T Subsystem
        if (OI.getInstance().isInPits()) {
            SmartDashboard.putNumber(TelemetryNames.Shooter.leftMotorCurrent, leftMotor.getOutputCurrent());
            SmartDashboard.putNumber(TelemetryNames.Shooter.rightMotorCurrent, rightMotor.getOutputCurrent());
        }
    }

    @Override
    public void updatePreferences() {
        loadPreferences();

        if (leftPid != null) {
            leftPid.setP(pid_P, slotID);
            leftPid.setI(pid_I, slotID);
            leftPid.setD(pid_D, slotID);
            leftPid.setFF(pid_F, slotID);
            leftPid.setIZone(pid_IZone, slotID);
            leftPid.setIZone(pid_IMaxAccum, slotID);
        }
        if (rightPid != null) {
            rightPid.setP(pid_P, slotID);
            rightPid.setI(pid_I, slotID);
            rightPid.setD(pid_D, slotID);
            rightPid.setFF(pid_F, slotID);
            rightPid.setIZone(pid_IZone, slotID);
            rightPid.setIZone(pid_IMaxAccum, slotID);
        }

        // TODO - Should this also (re)set targetRpm?
    }

    @Override
    public void disable() {
        super.disable();

        logger.info("last value of RPM tolerance: {}", tolerance);
    }

    @Override
    public void stop() {
        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);

        // set() implemented by setReference() call
        leftMotor.set(0.0);
        rightMotor.set(0.0);

        isActive = false;
    }

    private String activePosition = "";

    @Override
    public String getActivePosition() {
        return activePosition;
    }

    @Override
    public void setTargetRpm(double rpm) {
        this.targetRpm = rpm; // Save off value for enabling

        if (isActive) {
            leftPid.setReference(targetRpm, ControlType.kVelocity, slotID);
            rightPid.setReference(targetRpm, ControlType.kVelocity, slotID);
        }
    }

    @Override
    public void shoot() {
        isActive = true;

        leftMotor.setIdleMode(IdleMode.kCoast);
        rightMotor.setIdleMode(IdleMode.kCoast);

        leftPid.setOutputRange(0.10, 1.0, slotID);
        rightPid.setOutputRange(0.10, 1.0, slotID);

        /* generated speed */
        leftPid.setReference(targetRpm, ControlType.kVelocity, slotID);
        rightPid.setReference(targetRpm, ControlType.kVelocity, slotID);
    }

    // FIXME - Was supposed to be for manual; no idleShooter scaling
    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
        case 21:
            leftMotor.set(idleShooter(speed));
            break;
        case 22:
            rightMotor.set(idleShooter(speed));
            break;
        case 29:
            leftMotor.setIdleMode(IdleMode.kBrake);
            rightMotor.setIdleMode(IdleMode.kBrake);
            // Not slaved
            leftMotor.set(idleShooter(speed));
            rightMotor.set(idleShooter(speed));
            break;
        default:
            break;
        }
    }

    private double idleShooter(double speed) {
        // Have to be connected to the field to idle
        if (!OI.getInstance().isFieldConnected()) {
            return 0.0;
        }

        // Dashboard provides scale for shooter speed
        // double scale = Preferences.getInstance().getDouble(Shooter.scale, 1.0);
        // speed *= scale;
        speed = Math.min(0.20, speed);
        return speed;
    }

    @Override
    public boolean atTargetVelocity() {
        // FIXME - Find a way to only get a change via GUI
        tolerance = SmartDashboard.getNumber(CommandingNames.Shooter.tolerance, 0.015);

        // double velocity = (leftEncoder.getVelocity() + rightEncoder.getVelocity()) /
        // 2;
        double velocity = leftEncoder.getVelocity();

        return (((Math.abs(targetRpm - velocity)) / targetRpm) <= tolerance);
    }

}
