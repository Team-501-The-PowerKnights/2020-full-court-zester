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
import frc.robot.subsystems.hopper.HopperFactory;
import frc.robot.subsystems.hopper.IHopperSubsystem;
import frc.robot.subsystems.intake.IIntakeSubsystem;
import frc.robot.subsystems.intake.IntakeFactory;

import riolog.RioLogger;

public class FirePoseVision extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FirePoseVision.class.getName());

    private final IVisionSensor vision;

    private final IShooterSubsystem shooter;
    private final IBallevatorSubsystem ballevator;
    private final IIntakeSubsystem intake;
    private final IHopperSubsystem hopper;

    public FirePoseVision() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        shooter = ShooterFactory.getInstance();
        ballevator = BallevatorFactory.getInstance();
        intake = IntakeFactory.getInstance();
        hopper = HopperFactory.getInstance();

        addRequirements(shooter, ballevator, intake, hopper);

        logger.info("constructed {}", getName());
    }

    @Override
    public void execute() {
        super.execute();

        intake.pullIn();
        hopper.agitate();

        shooter.shoot();

        boolean visionGood = (vision.isActive() && vision.isLocked()) || !(vision.isActive());
        if (visionGood && shooter.atTargetVelocity()) {
            ballevator.lift();
        } else {
            // ballevator.liftToLimit();
            ballevator.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        ballevator.stop();

        // Don't stop shooter (default is idle command)
        // shooter.stop();

        hopper.stop();
        intake.stop();
    }

}
