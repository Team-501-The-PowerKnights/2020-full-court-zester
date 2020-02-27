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
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class FirePosePreset extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FirePosePreset.class.getName());

    private IShooterSubsystem shooter;
    private IBallevatorSubsystem ballevator;

    private double targetRPM;

    public FirePosePreset() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        ballevator = BallevatorFactory.getInstance();

        addRequirements(shooter, ballevator);

        targetRPM = 0.0;

        logger.info("constructed {}", getName());
    }

    @Override
    public void initialize() {
        super.initialize();

        switch (shooter.getActivePosition()) {
            case "Near":
                targetRPM = 3050;
                break;

            case "Mid":
                targetRPM = 3200;
                break;

            case "Far":
                targetRPM = 3295;
                break;

            default:
                targetRPM = 0.0;
                break;
        }

        if (!(shooter.atTargetVelocity(targetRPM))) {
            shooter.shoot(targetRPM, shooter.getActivePosition());
        }
    }

    @Override
    public void execute() {
        super.execute();

        if (shooter.atTargetVelocity(targetRPM)) {
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
