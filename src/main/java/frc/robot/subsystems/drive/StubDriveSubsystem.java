/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.telemetry.TelemetryNames;

public class StubDriveSubsystem implements IDriveSubsystem {

    private static final String myName = TelemetryNames.Drive.name;

    private static IDriveSubsystem ourInstance;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Drive.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubDriveSubsystem();

        SmartDashboard.putBoolean(TelemetryNames.Drive.status, true);
    }

    public static IDriveSubsystem getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    public StubDriveSubsystem() {
    }

    @Override
    public void periodic() {
        // TODO Auto-generated method stub

    }

    @Override
    public Subsystem getWpiSubsystem() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }
        return (Subsystem) ourInstance;
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
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn, boolean constrained) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetEncoders() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getLeftEncoderClicks() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRightEncoderClicks() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double convertInchesToEncoderClicks(double inches) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setBrake(boolean brakeOn) {
        // TODO Auto-generated method stub

    }

}
