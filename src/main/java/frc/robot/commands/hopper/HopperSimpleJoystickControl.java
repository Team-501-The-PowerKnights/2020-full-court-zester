/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hopper;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommand;
import frc.robot.subsystems.hopper.IHopperSubsystem;
import frc.robot.subsystems.hopper.HopperFactory;

import riolog.RioLogger;

public class HopperSimpleJoystickControl extends PKCommand {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(HopperSimpleJoystickControl.class.getName());

  private IHopperSubsystem hopper;
  private OI oi;
  private double speed;

  public HopperSimpleJoystickControl() {
    logger.info("constructing {}", getName());

    hopper = HopperFactory.getInstance();
    oi = OI.getInstance();

    addRequirements(hopper);

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

    speed = oi.getHopperSpeed();

    hopper.agitate(speed);
  }

}