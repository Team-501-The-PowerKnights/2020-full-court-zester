/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;

public class StubShooterSubsystem extends BaseShooterSubsystem {

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Shooter.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubShooterSubsystem();

        SmartDashboard.putBoolean(TelemetryNames.Shooter.status, true);
    }

    public static IShooterSubsystem getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    public StubShooterSubsystem() {
    }

    @Override
    public void periodic() {
    }

    @Override
    public void validateCalibration() {
    }

    @Override
    public void updatePreferences() {
    }

    @Override
    public void disable() {
    }

    @Override
    public void updateTelemetry() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void shoot(double dist) {
    }

    @Override
    public void shoot() {
    }

    @Override
    public void setTurretAngle(double angle) {
    }

    @Override
    public void home() {
    }

}
