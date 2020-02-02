/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

import riolog.RioLogger;

class StubDriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(SuitcaseDriveSubsystem.class.getName());

    StubDriveSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
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
    public void setBrake(boolean brakeOn) {
    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn, boolean constrained) {
    }

    @Override
    public void followPath(Pose2d start, List<Translation2d> interiorWaypoints, Pose2d end) {
    }

}
