/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import frc.robot.subsystems.ISubsystem;

public interface ITurretSubsystem extends ISubsystem {

    public void stop();

    public void setTurretAngle(double angle);

    public void home();

    public void setSpeed(int canID, double speed);

    public void jogCW();

    public void jogCCW();

    public void setAngleFromVision();

    public void holdAngle();

    public boolean isAtAngle(double targetAngle);
}
