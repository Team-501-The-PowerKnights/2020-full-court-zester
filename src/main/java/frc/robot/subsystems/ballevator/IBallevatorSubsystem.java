/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.ballevator;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IBallevatorSubsystem extends ISubsystem {

    public void stop();

    public void lift();

    public void lower();

    public boolean isFull();

    public void liftToLimit();

}
