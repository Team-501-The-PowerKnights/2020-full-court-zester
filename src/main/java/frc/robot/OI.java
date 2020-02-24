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

import frc.robot.commands.DoNothingButton;
import frc.robot.commands.InvalidButton;
import frc.robot.commands.ballevator.BallevatorLift;
import frc.robot.commands.ballevator.BallevatorLower;
import frc.robot.commands.drive.DriveSwap;
import frc.robot.commands.turret.TurretHome;
import frc.robot.commands.turret.TurretBackCommand;
import frc.robot.commands.turret.TurretFrontCommand;
import frc.robot.commands.turret.TurretJogCCWCommand;
import frc.robot.commands.turret.TurretJogCWCommand;
import frc.robot.commands.turret.TurretRightCommand;
import frc.robot.commands.turret.TurretVisionAlign;
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
    private final Button driveSwapButton;

    private final Joystick operatorStick;
    private final Button firePoseButton;
    private final Button shooterRevButton;
    private final Button fieldPositionFullButton;
    private final Button fieldPositionTrenchButton;
    private final Button fieldPositionMidButton;
    private final Button ballevatorUpButton;
    private final Button ballevatorDownButton;
    private final Button turretOrientationBackButton;
    private final Button turretOrientationRightButton;
    private final Button turretOrientationFrontButton;
    private final Button turretJogClockwiseButton;
    private final Button turretJogCounterClockwiseButton;
    private final Button visionEnableButton;
    private final Button reserved14Button; // 14 - wheelPositionButton
    private final Button reserved15Button; // 15 - wheelRotationButton
    private final Button turretHomeButton;
    private final Button reserved17Button;
    private final Button reserved18Button;
    private final Button climberExtendButton;
    private final Button climberRetractButton;

    private OI() {
        logger.info("constructing {}", myName);

        driverStick = new Joystick(0);
        turboButton = new JoystickButton(driverStick, 5);
        crawlButton = new JoystickButton(driverStick, 6);
        driveSwapButton = new JoystickButton(driverStick, 3);
        // FIXME - Implement for only Driver stick
        // pitClimberEnableButton = new JoystickButton(driverStick, 8);
        // pitClimberRetractButton = new JoystickButton(operatorStick, 8);

        operatorStick = new Joystick(1);
        firePoseButton = new JoystickButton(operatorStick, 1);
        shooterRevButton = new JoystickButton(operatorStick, 2);
        fieldPositionFullButton = new JoystickButton(operatorStick, 3);
        fieldPositionTrenchButton = new JoystickButton(operatorStick, 4);
        fieldPositionMidButton = new JoystickButton(operatorStick, 5);
        ballevatorUpButton = new JoystickButton(operatorStick, 6);
        ballevatorDownButton = new JoystickButton(operatorStick, 7);
        turretOrientationBackButton = new JoystickButton(operatorStick, 8);
        turretOrientationRightButton = new JoystickButton(operatorStick, 9);
        turretOrientationFrontButton = new JoystickButton(operatorStick, 10);
        turretJogClockwiseButton = new JoystickButton(operatorStick, 11);
        turretJogCounterClockwiseButton = new JoystickButton(operatorStick, 12);
        visionEnableButton = new JoystickButton(operatorStick, 13);
        reserved14Button = new JoystickButton(operatorStick, 14);
        reserved15Button = new JoystickButton(operatorStick, 15);
        turretHomeButton = new JoystickButton(operatorStick, 16);
        reserved17Button = new JoystickButton(operatorStick, 17);
        reserved18Button = new JoystickButton(operatorStick, 18);
        climberExtendButton = new JoystickButton(operatorStick, 17);
        climberRetractButton = new JoystickButton(operatorStick, 18);

        logger.info("constructed");
    }

    public void configureButtonBindings() {
        //@formatter:off
        /*
         * whenPressed() - starts command when newly pressed
         * whileHeld() - starts the command continuously while pressed
         * whenHeld() - starts the command when pressed; cancels when released
         * whenReleased() - starts the command when released
         * toggleWhenPressed()
         * cancelWhenPressed()
         */
        //@formatter:on

        // turboButton - implemented in getting values speed & turn
        // crawlButton - implemented in getting values speed & turn
        driveSwapButton.whenPressed(new DriveSwap());

        /*
         * Shooter Prep
         */
        firePoseButton.whenHeld(new DoNothingButton("firePoseButton"));
        shooterRevButton.whenHeld(new DoNothingButton("shooterRevButton"));
        visionEnableButton.whenHeld(new TurretVisionAlign());

        /*
         * Field Position
         */
        fieldPositionFullButton.whenPressed(new DoNothingButton("fieldPositionFullButton"));
        fieldPositionTrenchButton.whenPressed(new DoNothingButton("fieldPositionTrenchButton"));
        fieldPositionMidButton.whenPressed(new DoNothingButton("fieldPositionMidButton"));

        /*
         * Ballevator
         */
        ballevatorUpButton.whenHeld(new BallevatorLift());
        ballevatorDownButton.whenHeld(new BallevatorLower());

        /*
         * Turret Manual Control
         */
        turretHomeButton.whenPressed(new TurretHome());
        turretOrientationBackButton.whenPressed(new TurretBackCommand());
        turretOrientationRightButton.whenPressed(new TurretRightCommand());
        turretOrientationFrontButton.whenPressed(new TurretFrontCommand());
        turretJogClockwiseButton.whenPressed(new TurretJogCWCommand());
        turretJogCounterClockwiseButton.whenPressed(new TurretJogCCWCommand());

        /*
         * Climber
         */
        climberExtendButton.whenPressed(new DoNothingButton("climberExtendButton"));
        climberRetractButton.whenHeld(new DoNothingButton("climberRetractButton"));

        /*
         * Reserved
         */
        reserved14Button.whenPressed(new InvalidButton("reserved15Button"));
        reserved15Button.whenPressed(new InvalidButton("reserved16Button"));
        reserved17Button.whenPressed(new InvalidButton("reserved17Button"));
        reserved18Button.whenPressed(new InvalidButton("reserved18Button"));
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
            calcSpeed = hmiSpeed *= 0.50;
        }
        return calcSpeed;
    }

    public double getDriveTurn() {
        double hmiTurn = getRawDriveTurn();
        double calcTurn;
        if (turboButton.get()) {
            calcTurn = hmiTurn * 0.60; // * 0.50 in 2019
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
        return deadBand(getDriverBumperAxis(), 0.05);
    }

    public double getRawTurretSpeed() {
        return deadBand(getOperatorLeftXAxis(), 0.05);
    }

    public double getTurretSpeed() {
        // FIXME - No longer same button scheme or manual control
        double hmiSpeed = getRawTurretSpeed();
        double calcSpeed;
        // if (turretTurboButton.get()) {
        // calcSpeed = hmiSpeed * 0.35;
        // } else {
        calcSpeed = hmiSpeed * 0.15;
        // }
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
        // int value = operatorStick.getPOV();
        // if (value == 0) {
        // return 1;
        // // FIXME - No longer using both control for this
        // } else if ((pitClimberEnableButton.get()) && (pitClimberRetractButton.get()))
        // {
        // return 2;
        // } else {
        return 0;
        // }
    }

    //////////////////////////////////////////////////////////////////
    // TODO - Finish cleaning up these
    //////////////////////////////////////////////////////////////////

    public double getHopperSpeed() {
        return deadBand(getDriverBumperAxis(), 0.05);
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
