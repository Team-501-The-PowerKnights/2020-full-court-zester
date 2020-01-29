/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

public class SuitcaseGyroSensor extends AHRSGyro implements IGyroSensor {
  
  /* Our classes logger */
  private static final Logger logger = RioLogger.getLogger(GyroSensor.class.getName());

  private static final String myName = TelemetryNames.Gyro.name;

  private static IGyroSensor ourInstance;

  public static synchronized void constructInstance() {
      SmartDashboard.putBoolean(TelemetryNames.Gyro.status, false);

      if (ourInstance != null) {
          throw new IllegalStateException(myName + " already constructed");
      }

      ourInstance = new GyroSensor();

      SmartDashboard.putBoolean(TelemetryNames.Gyro.status, true);
  }

  public static IGyroSensor getInstance() {

      if (ourInstance == null) {
          throw new IllegalStateException(myName + " not constructed yet");
      }

      return ourInstance;
  }

  private static final boolean kGyroReversed = true;

  public SuitcaseGyroSensor() {
      logger.info("constructing");

      logger.info("constructed");
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
  public void updateTelemetry() {
      double value = getRoll();
      SmartDashboard.putNumber(TelemetryNames.Gyro.roll, value);
      value = getPitch();
      SmartDashboard.putNumber(TelemetryNames.Gyro.pitch, value);
      value = getYaw();
      SmartDashboard.putNumber(TelemetryNames.Gyro.yaw, value);
  }

  @Override
  public double getRoll() {
      return ahrs.getRoll();
  }

  @Override
  public double getPitch() {
      return ahrs.getPitch();
  }

  @Override
  public double getYaw() {
      return ahrs.getYaw();
  }

  @Override
  public double getHeading() {
      return Math.IEEEremainder(ahrs.getAngle(), 360) * (kGyroReversed ? -1 : 1);
  }

  @Override
  public double getAngle() {
      return ahrs.getAngle();
  }
}
