/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class TurretSimpleJoystickControl extends PKManualCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(TurretSimpleJoystickControl.class.getName());

    // Handle to our subsystem
    private IShooterSubsystem shooter;

    private double increment;
    private double angle;

    public TurretSimpleJoystickControl() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        addRequirements(shooter);

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        angle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        increment = oi.getTurretIncrement();

        shooter.setTurretAngle(/* angle += increment */increment);
    }

}