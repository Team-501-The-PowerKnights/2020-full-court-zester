/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IDriveSubsystem extends ISubsystem {

    public void stop();

    /**
     * 
     * @param hmiSpeed
     * @param hmiTurn
     */
    public void drive(double hmiSpeed, double hmiTurn);

    /**
     * 
     * @param hmiSpeed
     * @param hmiTurn
     * @param constrained
     */
    public void drive(double hmiSpeed, double hmiTurn, boolean constrained);

    public void resetEncoders();

    public double getLeftEncoderClicks();

    public double getRightEncoderClicks();

    public double convertInchesToEncoderClicks(double inches);

    public void setBrake(boolean brakeOn);

}
