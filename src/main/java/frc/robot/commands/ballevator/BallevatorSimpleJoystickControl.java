/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballevator;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommand;
import frc.robot.subsystems.ballevator.IBallevatorSubsystem;
import frc.robot.subsystems.ballevator.BallevatorFactory;

import riolog.RioLogger;

public class BallevatorSimpleJoystickControl extends PKCommand {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(BallevatorSimpleJoystickControl.class.getName());

  private IBallevatorSubsystem ballevator;
  private OI oi;
  private double speed;

  public BallevatorSimpleJoystickControl() {
    logger.info("constructing {}", getName());

    ballevator = BallevatorFactory.getInstance();
    oi = OI.getInstance();

    addRequirements(ballevator);

    logger.info("constructed");
  }

  @Override
  public void initialize() {
    super.initialize();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    super.execute();

    speed = oi.getBallevatorSpeed();

    if (speed > 0.05) {
        ballevator.lift();
    } else if (speed < 0.05) {
        ballevator.lower();
    } else {
        ballevator.stop();
    }
  }

}