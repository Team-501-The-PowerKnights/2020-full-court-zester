/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
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

    public void resetOdometry();

    public void resetOdometry(final Pose2d pose);

    public double getLeftEncoderClicks();

    public double getRightEncoderClicks();

    public double convertInchesToEncoderClicks(double inches);

    public void setBrake(boolean brakeOn);

    public Command getRamseteCommand(final Pose2d start, final List<Translation2d> interiorWaypoints,
    final Pose2d end);

}
