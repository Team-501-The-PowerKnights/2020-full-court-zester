/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.ballevator;

import org.slf4j.Logger;

import riolog.RioLogger;

class StubBallevatorSubsystem extends BaseBallevatorSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubBallevatorSubsystem.class.getName());

    public StubBallevatorSubsystem() {
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
    public void lift() {

    }

    @Override
    public void lower() {

    }

}
