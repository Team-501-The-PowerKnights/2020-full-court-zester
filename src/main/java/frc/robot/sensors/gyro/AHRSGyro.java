/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import com.kauailabs.navx.frc.AHRS;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

import riolog.RioLogger;

/**
 * A wrapper class around the navX-MXP sensor so some of the method calls which
 * take a finite amount of time to execute can be managed (i.e., waited for).
 */
class AHRSGyro {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(AHRSGyro.class.getName());

    AHRS ahrs;

    boolean ahrsValid;

    AHRSGyro() {
        try {
            ahrs = new AHRS(SPI.Port.kMXP);
            ahrsValid = waitForAhrsConnection();
        } catch (final RuntimeException ex) {
            logger.error("Instantiating naxX MXP" + ex.getMessage());
            DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);

            ahrs = null;
            ahrsValid = false;
        }
        SmartDashboard.putBoolean("ahrsValid", ahrsValid);
        if (ahrsValid) {
            configureAHRS();
        }
    };

    /**
     * Holds the constructor until we achieve a connection to the AHRS. Because it
     * takes some finite amount of time to accomplish this (seems to be between 50
     * and 100 msec), need to wait until it happens. We bail if it takes more than
     * 10 tries (500 msec) as it probably never will.
     *
     * @return
     **/
    protected boolean waitForAhrsConnection() {
        long count = 0;
        while (!ahrs.isConnected()) {
            logger.debug("waiting ... ahrs not connected");
            try {
                Thread.sleep(50);
            } catch (final InterruptedException ex) {
                // Don't care
            }
            if (++count > 10) {
                logger.warn("connect - failed to finish count={}", count);
                break;
            }
        }
        final boolean value = ahrs.isConnected();
        if (value) {
            logger.debug("ahrs connected");
        }
        return value;
    }

    protected void configureAHRS() {
        zeroYaw();
    }

    /**
     * Zero the yaw setting on the AHRS. Because it takes some finite amount of time
     * to accomplish this (seems to be between 50 and 100 msec), need to wait until
     * it happens and settles out. Because the robot can still be moving, need to
     * keep forcing it. We also bail if it takes more than 10 tries (500 msec) but
     * it probably never will.
     **/
    private void zeroYaw() {
        logger.debug("yaw={}", ahrs.getYaw());
        long count = 0;
        while (Math.abs(ahrs.getYaw()) > 1.0) {
            ahrs.zeroYaw();
            try {
                logger.debug("zeroYaw::waiting ... yaw={}", ahrs.getYaw());
                Thread.sleep(50);
            } catch (final InterruptedException ex) {
                // Don't care
            }
            if (++count > 10) {
                logger.warn("zeroYaw - failed to finish count={}", count);
                break;
            }
        }
        logger.debug("zeroYaw::done ... yaw={}", ahrs.getYaw());
        logger.trace("zeroYaw: done ... pidGet={}", ahrs.pidGet());
        logger.trace("zeroYaw::done ... angle={}", ahrs.getAngle());
    }

}
