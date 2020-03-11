/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.turret.ITurretSubsystem;
import frc.robot.subsystems.turret.TurretFactory;

import riolog.RioLogger;

public class FineAdjustTurrentPosition extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FineAdjustTurrentPosition.class.getName());

    // Handle to Turret (we need but don't require)
    private final ITurretSubsystem turret;
    // Handle to OI
    private final OI oi;

    // Initial value of the pot when command initialized
    private double initVal;
    // Last value of the pot read
    private double lastVal;
    // Flag for whether pot is considered 'active'
    private boolean isActive;

    public FineAdjustTurrentPosition() {
        logger.info("constructing {}", getName());

        turret = TurretFactory.getInstance();
        oi = OI.getInstance();

        logger.info("constructed");
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        initVal = oi.getTurretFineAdjustment();
        lastVal = initVal;

        isActive = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double currentVal = oi.getTurretFineAdjustment();

        // When pot is moved 5% from initial value; consider it 'active'
        if (!isActive && ((Math.abs(currentVal - initVal) / initVal) <= 0.05)) {
            isActive = true;
        }

        if (isActive && (lastVal != currentVal)) {
            turret.fineTuneTargetAngle(currentVal);
        }

        lastVal = currentVal;
    }

}
