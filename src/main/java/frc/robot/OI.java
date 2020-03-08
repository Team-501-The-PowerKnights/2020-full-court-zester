/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.FirePoseVision;
import frc.robot.commands.InvalidButton;
import frc.robot.commands.shooter.ShooterSetFar;
import frc.robot.commands.shooter.ShooterSetMid;
import frc.robot.commands.shooter.ShooterSetNear;
import frc.robot.commands.shooter.ShooterSetVeryFar;
import frc.robot.commands.PKParallelCommandGroup;
import frc.robot.commands.ballevator.BallevatorLift;
import frc.robot.commands.ballevator.BallevatorLower;
import frc.robot.commands.climber.ClimberClimb;
import frc.robot.commands.climber.ClimberExtend;
import frc.robot.commands.climber.ClimberRetractInPit;
import frc.robot.commands.drive.DriveSwap;
import frc.robot.commands.shooter.ShooterEnableSpin;
import frc.robot.commands.shooter.ShooterSpinUpFormula;
import frc.robot.commands.turret.TurretPositionBack;
import frc.robot.commands.turret.TurretPositionFront;
import frc.robot.commands.turret.TurretRotateCCW;
import frc.robot.commands.turret.TurretRotateCW;
import frc.robot.commands.turret.TurretPositionRight;
import frc.robot.commands.turret.TurretVisionAlign;
import frc.robot.commands.wheel.WheelRunClockwise;
import frc.robot.commands.wheel.WheelRunCounterClockwise;
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
    // Only in the pits
    private final Button pitClimberEnableButton;
    private final Button pitClimberRetractButton;

    private final Joystick operatorStick;
    private final Button firePoseButton;
    private final Button shooterRevButton;
    private final Button fieldPositionFarButton;
    private final Button fieldPositionMidButton;
    private final Button fieldPositionNearButton;
    private final Button ballevatorUpButton;
    private final Button ballevatorDownButton;
    private final Button turretOrientationBackButton;
    private final Button turretOrientationRightButton;
    private final Button turretOrientationFrontButton;
    private final Button turretJogClockwiseButton;
    private final Button turretJogCounterClockwiseButton;
    private final Button visionEnableButton;
    private final Button wheelClockwiseButton; // 14 - wheelPositionButton
    private final Button wheelCounterClockwiseButton; // 15 - wheelRotationButton
    private final Button turretHomeButton;
    private final Button reserved17Button;
    private final Button reserved18Button;
    private final Button climberExtendButton;
    private final Button climberClimbButton;

    private OI() {
        logger.info("constructing {}", myName);

        driverStick = new Joystick(0);
        turboButton = new JoystickButton(driverStick, 5);
        crawlButton = new JoystickButton(driverStick, 6);
        driveSwapButton = new JoystickButton(driverStick, 3);
        // Only for use in the pit
        pitClimberRetractButton = new JoystickButton(driverStick, 7);
        pitClimberEnableButton = new JoystickButton(driverStick, 8);

        operatorStick = new Joystick(1);
        firePoseButton = new JoystickButton(operatorStick, 1);
        shooterRevButton = new JoystickButton(operatorStick, 2);
        fieldPositionFarButton = new JoystickButton(operatorStick, 3);
        fieldPositionMidButton = new JoystickButton(operatorStick, 5);
        fieldPositionNearButton = new JoystickButton(operatorStick, 4);
        ballevatorUpButton = new JoystickButton(operatorStick, 6);
        ballevatorDownButton = new JoystickButton(operatorStick, 7);
        turretOrientationBackButton = new JoystickButton(operatorStick, 8);
        turretOrientationRightButton = new JoystickButton(operatorStick, 9);
        turretOrientationFrontButton = new JoystickButton(operatorStick, 10);
        turretJogClockwiseButton = new JoystickButton(operatorStick, 11);
        turretJogCounterClockwiseButton = new JoystickButton(operatorStick, 12);
        visionEnableButton = new JoystickButton(operatorStick, 13);
        wheelClockwiseButton = new JoystickButton(operatorStick, 14);
        wheelCounterClockwiseButton = new JoystickButton(operatorStick, 15);
        turretHomeButton = new JoystickButton(operatorStick, 16);
        reserved17Button = new JoystickButton(operatorStick, 17);
        reserved18Button = new JoystickButton(operatorStick, 18);
        climberExtendButton = new JoystickButton(operatorStick, 19);
        climberClimbButton = new JoystickButton(operatorStick, 20);

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

        pitClimberRetractButton.whenHeld(new ClimberRetractInPit());

        /*
         * Vision
         */
        visionEnableButton.whenHeld(new PKParallelCommandGroup(new TurretVisionAlign(), new ShooterSpinUpFormula()));

        /*
         * Field Position
         */
        fieldPositionFarButton.whenPressed(new ShooterSetFar());
        fieldPositionMidButton.whenPressed(new ShooterSetMid());
        fieldPositionNearButton.whenPressed(new ShooterSetNear());

        /*
         * Ballevator
         */
        ballevatorUpButton.whenHeld(new BallevatorLift());
        ballevatorDownButton.whenHeld(new BallevatorLower());

        /*
         * Turret
         */
        // turretHomeButton.whenPressed(new TurretHome());
        turretHomeButton.whenPressed(new ShooterSetVeryFar());
        turretOrientationBackButton.whenPressed(new TurretPositionBack());
        turretOrientationRightButton.whenPressed(new TurretPositionRight());
        turretOrientationFrontButton.whenPressed(new TurretPositionFront());
        // turretJogClockwiseButton.whenPressed(new TurretJogCW());
        // turretJogCounterClockwiseButton.whenPressed(new TurretJogCCW());
        turretJogClockwiseButton.whenHeld(new TurretRotateCW());
        turretJogCounterClockwiseButton.whenHeld(new TurretRotateCCW());

        /*
         * Shooter
         */
        shooterRevButton.whenHeld(new ShooterEnableSpin());

        /*
         * Climber
         */
        // climberExtendButton.whenPressed(new ClimberExtend());
        climberExtendButton.whenHeld(new ClimberExtend());
        climberClimbButton.whenHeld(new ClimberClimb());

        /*
         * Poses
         */
        firePoseButton.whenHeld(new FirePoseVision());
        firePoseButton.whenReleased(new ShooterEnableSpin());

        /*
         * Wheel
         */

        wheelClockwiseButton.whenHeld(new WheelRunClockwise());
        wheelCounterClockwiseButton.whenHeld(new WheelRunCounterClockwise());

        /*
         * Reserved
         */

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

    // FIXME - Change for pit testing like field
    public boolean isFieldConnected() {
        // For Field Running
        return DriverStation.getInstance().isFMSAttached();

        // For Pit Testing (be careful)
        // return true; // FOR pit testing
    }

    /*****************
     * Drive
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
        final double hmiTurn = getRawDriveTurn();
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

    /*****************
     * Power Cells
     *****************/

    public double getIntakeSpeed() {
        return deadBand(getDriverTriggerAxis(), 0.05);
    }

    public boolean getHopperActive() {
        return deadBand(getDriverTriggerAxis(), 0.05) != 0;
    }

    public boolean getBallevatorActive() {
        return deadBand(getDriverTriggerAxis(), 0.05) > 0;
    }

    /*****************
     * Turret
     *****************/

    public double getTurretFineAdjustment() {
        return deadBand(getOperatorPotentiometer2(), 0.05);
    }

    /*****************
     * Shooter
     *****************/

    public double getShooterFineAdjustment() {
        return deadBand(getOperatorPotentiometer3(), 0.05);
    }

    /*****************
     * Climber
     *****************/

    public boolean getClimberRetractEnable() {
        return pitClimberEnableButton.get();
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
    private final double deadBand(final double value, final double cutOff) {
        double retValue;
        if (value < cutOff && value > (cutOff * (-1))) {
            retValue = 0;
        } else {
            retValue = (value - (Math.abs(value) / value * cutOff)) / (1 - cutOff);
        }
        return retValue;
    }

    //////////////////////////////////////////////////////////////////
    // TODO - Finish cleaning up these
    //////////////////////////////////////////////////////////////////

    public boolean isShooterRevEnabled() {
        return shooterRevButton.get();
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

    private double getDriverTriggerAxis() {
        if (driverStick.getRawAxis(2) > 0.05) {
            return driverStick.getRawAxis(2);
        } else if (driverStick.getRawAxis(3) > 0.05) {
            return -driverStick.getRawAxis(3);
        } else {
            return 0;
        }
    }

    private double getOperatorPotentiometer2() {
        return operatorStick.getRawAxis(2);
    }

    private double getOperatorPotentiometer3() {
        return operatorStick.getRawAxis(3);
    }

}
