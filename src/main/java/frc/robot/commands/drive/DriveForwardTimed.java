/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import frc.robot.commands.PKCommand;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DriveForwardTimed extends PKCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveForwardTimed.class.getName());

    // Handle to our subsystem
    private IDriveSubsystem drive;

    //
    private long executeCount;

    /**
     * Creates a new DriveJoystickControl.
     */
    public DriveForwardTimed() {
        logger.info("constructing {}", getName());

        drive = DriveFactory.getInstance();
        addRequirements(drive);

        logger.info("constructed");
    }

    // Called once just before this Command runs the first time
    @Override
    public void initialize() {
        super.initialize();

        // 4 seconds = 200 * 20 msec (@ 50 Hz)
        executeCount = 200;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double speed = 0.4;
        double turn = 0.0;

        drive.drive(speed, turn);

        --executeCount;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (executeCount > 0 ? false : true);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drive
        drive.stop();

        super.end(interrupted);
    }

}
