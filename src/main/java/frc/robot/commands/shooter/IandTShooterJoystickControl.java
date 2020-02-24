/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.IIandTCommand;
import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class IandTShooterJoystickControl extends PKManualCommand implements IIandTCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IandTShooterJoystickControl.class.getName());

    // Handle to our subsystem
    private IShooterSubsystem shooter;

    /**
     * Creates a new DriveJoystickControl.
     */
    public IandTShooterJoystickControl() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        addRequirements(shooter);

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getDriveSpeed();

        shooter.setSpeed(20, speed);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
