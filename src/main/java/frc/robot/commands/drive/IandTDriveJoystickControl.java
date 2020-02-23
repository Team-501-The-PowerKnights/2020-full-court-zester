/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import frc.robot.commands.IIandTCommand;
import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class IandTDriveJoystickControl extends PKManualCommand implements IIandTCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveJoystickControl.class.getName());

    // Handle to our subsystem
    private IDriveSubsystem drive;

    /**
     * Creates a new DriveJoystickControl.
     */
    public IandTDriveJoystickControl() {
        logger.info("constructing {}", getName());

        drive = DriveFactory.getInstance();
        addRequirements(drive);

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getDriveSpeed();
        double turn = oi.getDriveTurn();

        drive.drive(11, speed);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
