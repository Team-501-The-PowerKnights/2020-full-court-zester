/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.hopper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

public class HopperSubsystem extends BaseHopperSubsystem {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(HopperSubsystem.class.getName());

  public static synchronized void constructInstance() {
    SmartDashboard.putBoolean(TelemetryNames.Hopper.status, false);

    if (ourInstance != null) {
      throw new IllegalStateException(myName + " already constructed");
    }

    ourInstance = new HopperSubsystem();

    SmartDashboard.putBoolean(TelemetryNames.Hopper.status, true);
  }

  public static IHopperSubsystem getInstance() {

    if (ourInstance == null) {
      throw new IllegalStateException(myName + " not constructed yet");
    }

    return ourInstance;
  }

  private TalonSRX agitator;

  public HopperSubsystem() {
    logger.info("constructing");

    agitator = new TalonSRX(0);

    logger.info("constructed");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void updateTelemetry() {
    // TODO Auto-generated method stub

  }

  @Override
  public void stop() {
    // TODO Auto-generated method stub

  }

  @Override
  public void validateCalibration() {
    // TODO Auto-generated method stub

  }

  @Override
  public void updatePreferences() {
    // TODO Auto-generated method stub

  }

  @Override
  public void disable() {
    // TODO Auto-generated method stub

  }

  @Override
  public void agitate() {
    agitator.set(ControlMode.PercentOutput, 0.2); // TODO - Determine actual preset speed
  }

  @Override
  public void agitate(double speed) {
    agitator.set(ControlMode.PercentOutput, speed);
  }
}
