/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import riolog.RioLogger;

public class DriveJoystickControl extends DriveOICommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveJoystickControl.class.getName());

    /**
     * Creates a new DriveJoystickControl.
     */
    public DriveJoystickControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getDriveSpeed();
        double turn = oi.getDriveTurn();

        drive.drive(speed, turn);
    }

}
