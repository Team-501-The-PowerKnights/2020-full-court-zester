/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.home;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DigitalInput;

import riolog.RioLogger;

/**
 * Provides implementation of <code>IHomeSensor</code> for the <i>Real-Bot</i>.
 */
class HomeSensor extends BaseHomeSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(HomeSensor.class.getName());

    private DigitalInput home;

    HomeSensor() {
        logger.info("constructing");

        home = new DigitalInput(8);

        logger.info("constructed");
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
    public boolean get() {
        return home.get();
    }

    @Override
    public void updateTelemetry() {
    }

}
