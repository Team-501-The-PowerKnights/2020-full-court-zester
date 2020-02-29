/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import org.slf4j.Logger;

import riolog.RioLogger;

class StubTurretSubsystem extends BaseTurretSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubTurretSubsystem.class.getName());

    public StubTurretSubsystem() {
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
    public void setTurretAngle(double angle) {
        // Stub doesn't implement this
    }

    @Override
    public void home() {
        // Stub doesn't implement this
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // Stub doesn't implement this
    }

    @Override
    public void jogCW() {
        // Stub doesn't implement this
    }

    @Override
    public void jogCCW() {
        // Stub doesn't implement this
    }

    @Override
    public void setAngleFromVision() {
        // Stub doesn't implement this
    }

    @Override
    public void holdAngle() {
        // Stub doesn't implement this
    }

    @Override
    public boolean isAtAngle(double targetAngle) {
        // Stub doesn't implement this
        return false;
    }
}
