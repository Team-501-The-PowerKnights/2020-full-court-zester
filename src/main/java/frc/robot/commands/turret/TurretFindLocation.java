/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import frc.robot.sensors.turretlocation.ITurretLocationSensor;
import frc.robot.sensors.turretlocation.TurretLocationFactory;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class TurretFindLocation extends TurretCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(TurretFindLocation.class.getName());

    // Handle to the Turret position sensor
    private final ITurretLocationSensor location;

    // Speed to use while seeking position
    private final double speed;

    // Flag for whether fail safe exit
    private boolean failSafe = false;

    public TurretFindLocation(double speed) {
        logger.info("constructing {}", getName());

        location = TurretLocationFactory.getInstance();

        this.speed = speed;

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        failSafe = location.get();
        // TODO - Will this prevent a single execution?
        // cancel();
    }

    @Override
    public void execute() {
        super.execute();

        // TODO - Handle failSafe
        turret.setSpeed(20, speed);
    }

    @Override
    public boolean isFinished() {
        return location.get();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        turret.stop();
    }

}
