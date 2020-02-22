/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import org.slf4j.Logger;

import riolog.RioLogger;

public class StubIntakeSubsystem extends BaseIntakeSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubIntakeSubsystem.class.getName());

    public StubIntakeSubsystem() {
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
    public void pullIn() {
        // Stub doesn't implement this
    }

    @Override
    public void pullIn(double speed) {
        // Stub doesn't implement this
    }

    @Override
    public void pushOut() {
        // Stub doesn't implement this
    }

    @Override
    public void pushOut(double speed) {
        // Stub doesn't implement this
    }

}
