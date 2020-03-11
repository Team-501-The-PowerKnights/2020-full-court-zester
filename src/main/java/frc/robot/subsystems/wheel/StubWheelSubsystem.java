/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.wheel;

import org.slf4j.Logger;

import riolog.RioLogger;

class StubWheelSubsystem extends BaseWheelSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubWheelSubsystem.class.getName());

    public StubWheelSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // Stub doesn't implement this
    }

    @Override
    public void stop() {
        // Stub doesn't implement this
    }

    @Override
    public void runCounterClockwise() {
        // Stub doesn't implement this
    }

    @Override
    public void runCounterClockwise(double speed) {
        // Stub doesn't implement this
    }

    @Override
    public void runClockwise() {
        // Stub doesn't implement this
    }

    @Override
    public void runClockwise(double speed) {
        // Stub doesn't implement this
    }

}
