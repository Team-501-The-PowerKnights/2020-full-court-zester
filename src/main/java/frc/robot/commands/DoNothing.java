/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Set;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj2.command.Subsystem;
import riolog.RioLogger;

public class DoNothing extends PKCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DoNothing.class.getName());

    public DoNothing() {
        logger.info("constructing {}", getName());
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        // TODO Auto-generated method stub
        return null;
    }

}
