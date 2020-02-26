/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.turrethome;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DigitalInput;

import riolog.RioLogger;

/**
 * Provides implementation of <code>ITurretHomeSensor</code> for the <i>Real-Bot</i>.
 */
class TurretHomeSensor extends BaseTurretHomeSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(TurretHomeSensor.class.getName());

    private DigitalInput turrethome;

    TurretHomeSensor() {
        logger.info("constructing");

        turrethome = new DigitalInput(8);

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
        return turrethome.get();
    }

    @Override
    public void updateTelemetry() {
    }

}
