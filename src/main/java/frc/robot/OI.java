/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class OI implements ITelemetryProvider {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(OI.class.getName());

    /** Singleton instance of class for all to use **/
    private static OI ourInstance;
    /** Name of our subsystem **/
    private final static String myName = TelemetryNames.OI.name;

    public static synchronized void constructInstance() {
        SmartDashboard.putNumber(TelemetryNames.OI.status, PKStatus.unknown.tlmValue);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        SmartDashboard.putNumber(TelemetryNames.OI.status, PKStatus.inProgress.tlmValue);

        ourInstance = new OI();

        SmartDashboard.putNumber(TelemetryNames.OI.status, PKStatus.success.tlmValue);
    }

    public static OI getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }
        return ourInstance;
    }

    private final Joystick driverStick;
    private final Button turboButton;
    private final Button crawlButton;

    private final Joystick operatorStick;
    private final Button turretTurboButton;

    private OI() {
        logger.info("constructing {}", myName);

        driverStick = new Joystick(0);
        turboButton = new JoystickButton(driverStick, 5);
        crawlButton = new JoystickButton(driverStick, 6);

        operatorStick = new Joystick(1);
        turretTurboButton = new JoystickButton(operatorStick, 5);

        configureButtonBindings();

        logger.info("constructed");
    }

    private void configureButtonBindings() {
        // TODO - What is this? Do we need it?

        // elbowHighButton = new POVButton( operatorStick, 0 );
        // elbowHighButton.whenPressed( new ElbowGoToHighPosition() );
        // elbowMidButton = new POVButton( operatorStick, 90 );
        // elbowMidButton.whenPressed( new ElbowGoToMidPosition() );
        // elbowLowButton = new POVButton( operatorStick, 180 );
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.HMI.rawSpeed, getRawDriveSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.rawTurn, getRawDriveTurn());
        SmartDashboard.putBoolean(TelemetryNames.HMI.turbo, turboButton.get());
        SmartDashboard.putBoolean(TelemetryNames.HMI.crawl, crawlButton.get());
        SmartDashboard.putNumber(TelemetryNames.HMI.oiSpeed, getDriveSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.oiTurn, getDriveTurn());
    }

    /*****************
     * Drive Stuff
     *****************/

    public double getRawDriveSpeed() {
        return deadBand(getDriverLeftYAxis(), 0.05);
    }

    public double getRawDriveTurn() {
        return deadBand(getDriverRightXAxis(), 0.05);
    }

    public double getDriveSpeed() {
        double hmiSpeed = getRawDriveSpeed();
        double calcSpeed;
        if (turboButton.get()) {
            calcSpeed = hmiSpeed; // * 0.80 in 2019
        } else if (crawlButton.get()) {
            calcSpeed = hmiSpeed * 0.30;
        } else {
            calcSpeed = hmiSpeed; // * 0.70;
        }
        return calcSpeed;
    }

    public double getDriveTurn() {
        double hmiTurn = getRawDriveTurn();
        double calcTurn;
        if (turboButton.get()) {
            calcTurn = hmiTurn; // * 0.50 in 2019
        } else if (crawlButton.get()) {
            calcTurn = hmiTurn * 0.25;
        } else {
            calcTurn = hmiTurn * 0.30;
        }
        return calcTurn;
    }

    /**
     * 
     * Lifted from:
     * https://www.chiefdelphi.com/t/how-do-i-program-a-joystick-deadband/122625
     * 
     * @param value
     * @param cutOff
     * @return
     */
    private final double deadBand(double value, double cutOff) {
        double retValue;
        if (value < cutOff && value > (cutOff * (-1))) {
            retValue = 0;
        } else {
            retValue = (value - (Math.abs(value) / value * cutOff)) / (1 - cutOff);
        }
        return retValue;
    }

    public double getIntakeSpeed() {
        double speed = 0.0;
        if (getDriverLeftTrigger() > 0) {
            speed = getDriverLeftTrigger();
        } else if (getDriverRightTrigger() > 0) {
            speed = -getDriverRightTrigger();
        }
        return speed;
    }

    public double getBallevatorSpeed() {
        double speed = 0.0;
        if (getOperatorLeftTrigger() > 0) {
            speed = getOperatorLeftTrigger();
        } else if (getOperatorRightTrigger() > 0) {
            speed = -getOperatorRightTrigger();
        }
        return speed;
    }

    public double getRawTurretSpeed() {
        return deadBand(getOperatorLeftXAxis(), 0.05);
    }

    public double getTurretSpeed() {
        double hmiSpeed = getRawTurretSpeed();
        double calcSpeed;
        if (turretTurboButton.get()) {
            calcSpeed = hmiSpeed * 0.35;
        } else {
            calcSpeed = hmiSpeed * 0.15;
        }
        return calcSpeed;
    }

    public double getShooterSpeed() {
        return deadBand(getOperatorRightYAxis(), 0.05);
    }

    /**
     * 
     * @return 0 - neutral, 1 - extend
     * 
     */
    public int getClimberCommand() {
        int value = operatorStick.getPOV();
        if (value == 0) {
            return 1;
            // } else if (value == 180) {
            // return 2;
        } else {
            return 0;
        }
    }

    //////////////////////////////////////////////////////////////////
    // TODO - Finish cleaning up these
    //////////////////////////////////////////////////////////////////

    public double getHopperSpeed() {
        return getDriverBumperAxis();
    }

    public double getTurretIncrement() {
        return getOperatorBumperAxis();
    }

    //////////////////////////////////////////////////////////////////
    // TODO - Finish cleaning up these
    //////////////////////////////////////////////////////////////////

    private double getDriverLeftYAxis() {
        return -driverStick.getRawAxis(1);
    }

    private double getDriverRightYAxis() {
        return -driverStick.getRawAxis(5);
    }

    private double getDriverLeftXAxis() {
        return driverStick.getRawAxis(0);
    }

    private double getDriverRightXAxis() {
        return driverStick.getRawAxis(4);
    }

    private double getDriverLeftTrigger() {
        return driverStick.getRawAxis(2);
    }

    private double getDriverRightTrigger() {
        return driverStick.getRawAxis(3);
    }

    private double getDriverLeftBumper() {
        return driverStick.getRawAxis(2);
    }

    private double getDriverRightBumper() {
        return driverStick.getRawAxis(3);
    }

    private double getDriverBumperAxis() {
        if (driverStick.getRawAxis(2) > 0.05) {
            return driverStick.getRawAxis(2);
        } else if (driverStick.getRawAxis(3) > 0.05) {
            return -driverStick.getRawAxis(3);
        } else {
            return 0;
        }
    }

    private double getOperatorLeftYAxis() {
        return -operatorStick.getRawAxis(1);
    }

    private double getOperatorRightYAxis() {
        return -operatorStick.getRawAxis(5);
    }

    private double getOperatorLeftXAxis() {
        return operatorStick.getRawAxis(0);
    }

    private double getOperatorRightXAxis() {
        return operatorStick.getRawAxis(4);
    }

    private double getOperatorLeftBumper() {
        return operatorStick.getRawAxis(2);
    }

    private double getOperatorRightBumper() {
        return operatorStick.getRawAxis(3);
    }

    private double getOperatorBumperAxis() {
        if (operatorStick.getRawAxis(2) > 0.05) {
            return operatorStick.getRawAxis(2);
        } else if (operatorStick.getRawAxis(3) > 0.05) {
            return -operatorStick.getRawAxis(3);
        } else {
            return 0;
        }
    }

    private double getOperatorLeftTrigger() {
        return operatorStick.getRawAxis(2);
    }

    private double getOperatorRightTrigger() {
        return operatorStick.getRawAxis(3);
    }

}
