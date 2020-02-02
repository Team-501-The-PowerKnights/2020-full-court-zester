/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommand;
import frc.robot.subsystems.intake.IIntakeSubsystem;
import frc.robot.subsystems.intake.IntakeFactory;

import riolog.RioLogger;

public class IntakeSimpleJoystickControl extends PKCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IntakeSimpleJoystickControl.class.getName());

    private IIntakeSubsystem intake;
    private OI oi;
    private double speed;

    public IntakeSimpleJoystickControl() {
        logger.info("constructing {}", getName());

        intake = IntakeFactory.getInstance();
        oi = OI.getInstance();

        addRequirements(intake);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        speed = oi.getDriverBumperAxis();

        if (speed > 0) {
            intake.pushOut(speed);
        } else if (speed < 0) {
            intake.pullIn(speed);
        }
    }

}
