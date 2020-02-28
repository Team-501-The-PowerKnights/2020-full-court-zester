/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import org.slf4j.Logger;

import riolog.RioLogger;

class StubShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubShooterSubsystem.class.getName());

    public StubShooterSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // Stub doesn't implement this
    }

    @Override
    public void updateTelemetry() {
        // Stub doesn't implement this
    }

    @Override
    public void validateCalibration() {
        // Stub doesn't implement this
    }

    @Override
    public void updatePreferences() {
        // Stub doesn't implement this
    }

    @Override
    public void disable() {
        // Stub doesn't implement this
    }

    @Override
    public void stop() {
        // Stub doesn't implement this
    }

    @Override
    public void setTargetRpm(double rpm) {
        // Stub doesn't implement this
    }

    @Override
    public void shoot() {
        // Stub doesn't implement this
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // Stub doesn't implement this
    }

    @Override
    public boolean atTargetVelocity() {
        // Stub does nothing useful; returns false
        return false;
    }

    @Override
    public String getActivePosition() {
        // Stub doesn't implement this; returns an empty string
        return "";
    }

}
