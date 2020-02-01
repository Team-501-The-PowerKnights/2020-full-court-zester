/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

class SuitcaseDriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(SuitcaseDriveSubsystem.class.getName());

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

    private final TalonSRX leftFrontMotor;
    private final TalonSRX leftRearMotor;
    private final VictorSP rightFrontMotor;
    private final VictorSP rightRearMotor;

    private final SpeedControllerGroup right;

    SuitcaseDriveSubsystem() {
        logger.info("constructing");

        leftFrontMotor = new TalonSRX(12);
        leftRearMotor = new TalonSRX(13);
        rightFrontMotor = new VictorSP(1);
        rightRearMotor = new VictorSP(0);

        leftRearMotor.follow(leftFrontMotor);
        right = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);

        leftFrontMotor.setInverted(false);
        right.setInverted(true);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
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
    public void periodic() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBrake(boolean brakeOn) {
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
    public void followPath(Pose2d start, List<Translation2d> interiorWaypoints, Pose2d end) {
        // TODO Auto-generated method stub

    }

}
