/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DriveForwardDistance extends DriveCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveForwardDistance.class.getName());

    private double targetClicks;

    public DriveForwardDistance(double distanceInFeet) {
        logger.info("constructing {}", getName());

        targetClicks = distanceInFeet * (1 / 3.281) // Conversion to meters
                * (1 / (2 * Math.PI * 0.1524 /* Wheel radius */)) // Convert to wheel revolutions (Circumference)
                * (1 /* Belt gearing */) // Convert to output shaft revolutions (Belt gearing)
                * (1 / 10.71 /* Gearbox gearing */); // Convert to motor revolutions (TB Mini gearing);

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double speed = 0.2;
        double turn = 0.0;

        drive.drive(speed, turn);
    }

    @Override
    public boolean isFinished() {
        return drive.getEncoderClicks() == targetClicks;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drive
        drive.stop();

        super.end(interrupted);
    }

}
