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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.slf4j.Logger;

import frc.robot.preferences.PreferenceNames.Shooter;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

class ShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterSubsystem.class.getName());

    /**
     * Mechanisms and sensors
     */

    private CANSparkMax shootMaster;
    private CANSparkMax shootSlave;
    private CANEncoder shootEncoder;
    private CANPIDController shooterPID;

    // private TalonSRX incrementer; // Unused for now

    /**
     * Creates a new ShooterSubsystem.
     */
    public ShooterSubsystem() {
        logger.info("constructing");

        shootMaster = new CANSparkMax(21, MotorType.kBrushless);
        shootMaster.restoreFactoryDefaults();
        shootSlave = new CANSparkMax(22, MotorType.kBrushless);
        shootSlave.restoreFactoryDefaults();
        // + spin out, - spin in

        // Slaved and inverted
        shootSlave.follow(shootMaster, true);

        shootEncoder = new CANEncoder(shootMaster);

        shooterPID = new CANPIDController(shootMaster);
        shooterPID.setP(pid_P);
        shooterPID.setI(pid_I);
        shooterPID.setD(pid_D);
        shooterPID.setFF(pid_F);

        // incrementer = new TalonSRX(23); // Unused for now

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Shooter.velocity, shootEncoder.getVelocity());
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        loadPreferences();

        if (shooterPID != null) {
            shooterPID.setP(pid_P);
            shooterPID.setI(pid_I);
            shooterPID.setD(pid_D);
            shooterPID.setFF(pid_F);
        }

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        shooterPID.setReference(0, ControlType.kVoltage);
        shootMaster.set(0.0);
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
    public void setSpeed(int canID, double speed) {
        switch (canID) {
        case 21:
            shootMaster.set(idleShooter(speed));
            break;
        case 22:
            shootSlave.set(idleShooter(speed));
            break;
        case 29:
            // Assuming slaved
            shootMaster.set(idleShooter(speed));
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
