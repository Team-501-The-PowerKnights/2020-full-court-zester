/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

public class IntakeSubsystem extends SubsystemBase implements ITelemetryProvider {

  private static final String myName = TelemetryNames.Intake.name;

  private static IntakeSubsystem ourInstance;

  public static synchronized void constructInstance() {
    SmartDashboard.putBoolean(TelemetryNames.Intake.status, false);

    if (ourInstance != null) {
      throw new IllegalStateException(myName + " already constructed");
    }

    ourInstance = new IntakeSubsystem();

    SmartDashboard.putBoolean(TelemetryNames.Intake.status, true);
  }

  public static IntakeSubsystem getInstance() {

    if (ourInstance == null) {
      throw new IllegalStateException(myName + " not constructed yet");
    }

    return ourInstance;
  }

  private TalonSRX intakeMotor;

  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {
    intakeMotor = new TalonSRX(RobotMap.kIntakePort);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Runs the intake at the speed passed to the method.
   * 
   * @param speed
   */
  public void runIntake(double speed) {
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void updateTelemetry() {
  }
}