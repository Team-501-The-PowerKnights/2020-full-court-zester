/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.sensors.turretlocation.TurretLocationFactory;
import frc.robot.sensors.wheelcolor.WheelColorFactory;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class SensorFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(SensorFactory.class.getName());

    public static List<ISensor> constructSensors() {
        logger.info("constructing");

        ArrayList<ISensor> sensors = new ArrayList<ISensor>();

        TelemetryManager tlmMgr = TelemetryManager.getInstance();

        SmartDashboard.putNumber(TelemetryNames.Gyro.status, PKStatus.unknown.tlmValue);
        {
            GyroFactory.constructInstance();
            ISensor s = GyroFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        SmartDashboard.putNumber(TelemetryNames.TurretLocation.status, PKStatus.unknown.tlmValue);
        {
            TurretLocationFactory.constructInstance();
            ISensor s = TurretLocationFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        SmartDashboard.putNumber(TelemetryNames.Vision.status, PKStatus.unknown.tlmValue);
        {
            VisionFactory.constructInstance();
            ISensor s = VisionFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        SmartDashboard.putNumber(TelemetryNames.WheelColor.status, PKStatus.unknown.tlmValue);
        {
            WheelColorFactory.constructInstance();
            ISensor s = WheelColorFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        logger.info("constructed");
        return sensors;
    }

}
