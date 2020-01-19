/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

public class WheelOfFortuneSubsystem extends SubsystemBase implements ITelemetryProvider {

  private static final String myName = TelemetryNames.WheelOfFortune.name;

  private static WheelOfFortuneSubsystem ourInstance;

  public static synchronized void constructInstance() {
    SmartDashboard.putBoolean(TelemetryNames.WheelOfFortune.status, false);

    if (ourInstance != null) {
      throw new IllegalStateException(myName + " already constructed");
    }

    ourInstance = new WheelOfFortuneSubsystem();

    SmartDashboard.putBoolean(TelemetryNames.WheelOfFortune.status, true);
  }

  public static WheelOfFortuneSubsystem getInstance() {

    if (ourInstance == null) {
      throw new IllegalStateException(myName + " not constructed yet");
    }

    return ourInstance;
  }

  private ColorSensorV3 colorSensor;
  private CANSparkMax wheel;

  /**
   * Creates a new WheelOfFortuneSubsystem.
   */
  public WheelOfFortuneSubsystem() {
    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

    wheel = new CANSparkMax(0, MotorType.kBrushless); // TODO - Add actual port number
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Method providing a handle to access color values within the class.
   * 
   * @return The color the sensor is seeing
   */
  private Color getColor() {
    return colorSensor.getColor();
  }

  /**
   * 
   * @return if the sensor senses that the wheel is on blue
   */
  private boolean isBlue() {
    return getColor() == Color.kBlue;
  }

  /**
   * 
   * @return if the sensor senses that the wheel is on green
   */
  private boolean isGreen() {
    return getColor() == Color.kGreen;
  }

  /**
   * 
   * @return if the sensor senses that the wheel is on yellow
   */
  private boolean isYellow() {
    return getColor() == Color.kYellow;
  }

  /**
   * 
   * @return if the sensor senses that the wheel is on red
   */
  private boolean isRed() {
    return getColor() == Color.kRed;
  }

  /**
   * Runs the wheel at a given speed.
   * 
   * @param speed
   */
  public void runWheel(double speed) {
    wheel.set(speed);
  }

  @Override
  public void updateTelemetry() {
    SmartDashboard.putString(TelemetryNames.WheelOfFortune.color, getColor().toString());
  }
}
