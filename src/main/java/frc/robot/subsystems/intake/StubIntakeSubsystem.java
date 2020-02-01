/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

public class StubIntakeSubsystem extends BaseIntakeSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(StubIntakeSubsystem.class.getName());

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Intake.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubIntakeSubsystem();

        SmartDashboard.putBoolean(TelemetryNames.Intake.status, true);
    }

    public static IIntakeSubsystem getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    public StubIntakeSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void periodic() {
    }

    @Override
    public void validateCalibration() {
    }

    @Override
    public void updatePreferences() {
    }

    @Override
    public void disable() {
    }

    @Override
    public void updateTelemetry() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void pullIn() {
    }

    @Override
    public void pullIn(double speed) {
    }

    @Override
    public void pushOut() {
    }

    @Override
    public void pushOut(double speed) {
    }

}
