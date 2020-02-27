/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.subsystems.ballevator.BallevatorFactory;
import frc.robot.subsystems.ballevator.IBallevatorSubsystem;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class FirePoseIncrement extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FirePoseIncrement.class.getName());

    private IShooterSubsystem shooter;
    private IBallevatorSubsystem ballevator;
    private IDriveSubsystem drive;

    private double targetRPM;
    private double targetClicks = 1 // 1 foot per 10 rpm increase
    * (1 / 3.281) // Conversion to meters
    * (1 / (2 * Math.PI * 0.1524 /* Wheel radius */)) // Convert to wheel revolutions (Circumference)
    * (1 /* Belt gearing */) // Convert to output shaft revolutions (Belt gearing)
    * (1 / 10.71 /* Gearbox gearing */); // Convert to motor revolutions (TB Mini gearing)
    private double previousClicks;

    public FirePoseIncrement() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        ballevator = BallevatorFactory.getInstance();
        drive = DriveFactory.getInstance();

        addRequirements(shooter, ballevator);

        targetRPM = 0.0;

        logger.info("constructed {}", getName());
    }

    @Override
    public void initialize() {
        super.initialize();

        targetRPM = 3050;
        previousClicks = 0.0;
    }

    @Override
    public void execute() {
        super.execute();

        shooter.shoot(targetRPM, "");

        if (shooter.atTargetVelocity(targetRPM)) {
            ballevator.lift();
        } else {
            ballevator.liftToLimit();
        }

        if ((drive.getEncoderClicks() - previousClicks) == targetClicks) {
            previousClicks = drive.getEncoderClicks();
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        ballevator.stop();
    }
}
