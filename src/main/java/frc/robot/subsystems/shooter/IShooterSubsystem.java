/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IShooterSubsystem extends ISubsystem {

    public void stop();

    public void shoot(double dist);

    public void shoot();

    public void setTurretAngle(double angle);

    public void home();

    public void setSpeed(int canID, double speed);

    public void jogCW();

    public void jogCCW();

    public void setAngleFromVision();

    public void setLED(int mode);

    public void setCamMode(boolean sight);
}
