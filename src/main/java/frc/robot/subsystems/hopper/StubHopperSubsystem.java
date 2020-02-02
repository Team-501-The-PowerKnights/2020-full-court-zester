/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.hopper;

import org.slf4j.Logger;

import riolog.RioLogger;

public class StubHopperSubsystem extends BaseHopperSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubHopperSubsystem.class.getName());

    public StubHopperSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
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
    public void agitate() {
    }

    @Override
    public void agitate(double speed) {
    }

}
