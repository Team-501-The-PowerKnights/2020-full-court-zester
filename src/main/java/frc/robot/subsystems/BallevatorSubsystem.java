/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.telemetry.TelemetryNames;

public class BallevatorSubsystem extends SubsystemBase {
  /**
   * Creates a new BallevatorSubsystem.
   */
  private static final String myName = TelemetryNames.Ballevator.name;

  private static BallevatorSubsystem ourInstance;

  public static synchronized void constructInstance() {
    SmartDashboard.putBoolean(TelemetryNames.Ballevator.status, false);

    if (ourInstance != null) {
      throw new IllegalStateException(myName + " already constructed");
    }

    ourInstance = new BallevatorSubsystem();

    SmartDashboard.putBoolean(TelemetryNames.Ballevator.status, true);
  }

  public static BallevatorSubsystem getInstance() {

    if (ourInstance == null) {
      throw new IllegalStateException(myName + " not constructed yet");
    }

    return ourInstance;
  }

  private TalonSRX ballevatorMotor;

  public BallevatorSubsystem() {
    ballevatorMotor = new TalonSRX(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runBallevator(double speed) {
    ballevatorMotor.set(ControlMode.PercentOutput, speed);
  }
}
