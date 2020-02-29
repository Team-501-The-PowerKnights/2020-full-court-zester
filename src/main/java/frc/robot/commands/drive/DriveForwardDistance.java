/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DriveForwardDistance extends DriveCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveForwardDistance.class.getName());

    // Distance to drive (from current position) in clicks
    private double distanceClicks;
    // Target position in clicks (current + distance)
    private double targetClicks;

    public DriveForwardDistance(double distanceInFeet) {
        logger.info("constructing {}", getName());

        distanceClicks = distanceInFeet * 6.849; // motor revolutions per foot

        SmartDashboard.putNumber(TelemetryNames.Drive.distanceClicks, distanceClicks);

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        targetClicks = drive.getEncoderClicks() + distanceClicks;
        SmartDashboard.putNumber(TelemetryNames.Drive.targetClicks, targetClicks);
    }

    @Override
    public void execute() {
        super.execute();

        double speed = 0.20;
        double turn = 0.0;

        drive.drive(speed, turn);
    }

    @Override
    public boolean isFinished() {
        return (drive.getEncoderClicks() >= targetClicks);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drive
        drive.stop();

        super.end(interrupted);
    }

}
