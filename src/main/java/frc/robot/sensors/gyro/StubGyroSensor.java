/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

public class StubGyroSensor implements IGyroSensor {

    private static final String myName = TelemetryNames.Gyro.name;

    private static IGyroSensor ourInstance;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubGyroSensor();

        SmartDashboard.putBoolean(TelemetryNames.Gyro.status, true);
    }

    public static IGyroSensor getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    public StubGyroSensor() {
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
        // TODO Auto-generated method stub

    }

}
