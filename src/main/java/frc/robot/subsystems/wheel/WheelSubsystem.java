/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.wheel;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sensors.wheelcolor.IWheelColorSensor;
import frc.robot.sensors.wheelcolor.WheelColorFactory;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

public class WheelSubsystem extends SubsystemBase implements ITelemetryProvider {

    private static final String myName = TelemetryNames.Wheel.name;

    private static WheelSubsystem ourInstance;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Wheel.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new WheelSubsystem();

        SmartDashboard.putBoolean(TelemetryNames.Wheel.status, true);
    }

    public static WheelSubsystem getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    private IWheelColorSensor colorSensor;

    private CANSparkMax wheel;

    /**
     * Creates a new WheelSubsystem.
     */
    public WheelSubsystem() {
        colorSensor = WheelColorFactory.getInstance();

        // wheel = new CANSparkMax(RobotMap.kWheelPort, MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
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
        // TODO Auto-generated method stub

    }

}
