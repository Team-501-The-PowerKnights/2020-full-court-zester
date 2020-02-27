/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.subsystems.ballevator.BallevatorFactory;
import frc.robot.subsystems.ballevator.IBallevatorSubsystem;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class FirePoseFormula extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FirePoseFormula.class.getName());

    private IShooterSubsystem shooter;
    private IBallevatorSubsystem ballevator;
    private IVisionSensor vision;

    private double targetRpm;

    public FirePoseFormula() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        ballevator = BallevatorFactory.getInstance();
        vision = VisionFactory.getInstance();

        addRequirements(shooter, ballevator);

        targetRpm = 0.0;

        logger.info("constructed {}", getName());
    }

    @Override
    public void initialize() {
        super.initialize();

        // double ty = vision.getY();

        // targetRpm = (13.5 * (ty * ty)) - (111.3 * ty) + 3352.4;

        targetRpm = 3200;

        shooter.shoot(targetRpm, "");
    }

    @Override
    public void execute() {
        super.execute();

        if (DriverStation.getInstance().getMatchTime() >= 10) {
            targetRpm = 3300;
            shooter.shoot(targetRpm, "");
        }

        if (shooter.atTargetVelocity(targetRpm) && vision.getLocked()) { // TODO - Implement rpm generation from
                                                                         // distance
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
