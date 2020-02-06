/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ModuleFactory {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final Logger logger = RioLogger.getLogger(ModuleFactory.class.getName());

    // TODO - Implement this
    public static List<IModule> constructModules() {

        ArrayList<IModule> modules = new ArrayList<IModule>();

        // TelemetryManager tlmMgr = TelemetryManager.getInstance();

        // PropertiesManager propsMgr;

        // SmartDashboard.putBoolean( TelemetryNames.PDB.status, false );
        // {
        // propsMgr = new PropertiesManager( PropertyNames.PDB.name );
        // boolean realModule =
        // propsMgr.getBoolean( PropertyNames.PDB.useRealModule );

        // if ( realModule )
        // {
        // PDBModule.constructInstance();
        // IModule ss = PDBModule.getInstance();
        // tlmMgr.addProvider( ss );
        // modules.add( ss );
        // }
        // else
        // {
        // logger.warn( "stub PDBModule being instantiated" );
        // frc.robot.modules.stubs.PDBModule.constructInstance();
        // }
        // }

        return modules;
    }

}
