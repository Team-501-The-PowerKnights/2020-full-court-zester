/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.hopper;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IHopperSubsystem extends ISubsystem {

    public void stop();

    public void agitate();

    /**
     * 
     * @param speed
     */
    public void agitate(double speed);

}
