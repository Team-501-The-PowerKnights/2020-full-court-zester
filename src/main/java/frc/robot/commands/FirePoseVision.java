/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.subsystems.ballevator.BallevatorFactory;
import frc.robot.subsystems.ballevator.IBallevatorSubsystem;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class FirePoseVision extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FirePoseVision.class.getName());

    private final IVisionSensor vision;

    private final IShooterSubsystem shooter;
    private final IBallevatorSubsystem ballevator;

    public FirePoseVision() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        shooter = ShooterFactory.getInstance();
        ballevator = BallevatorFactory.getInstance();

        addRequirements(shooter, ballevator);

        logger.info("constructed {}", getName());
    }

    @Override
    public void execute() {
        super.execute();

        shooter.shoot();

        boolean visionGood = vision.isLocked();
        if (visionGood && shooter.atTargetVelocity()) {
            ballevator.lift();
        } else {
            ballevator.liftToLimit();
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        ballevator.stop();
    }
}