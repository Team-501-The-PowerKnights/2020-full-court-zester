/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.properties;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;

public class PropertiesManager {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PropertiesManager.class.getName());

    private static PropertiesManager ourInstance;
    private static String myName = "Props";

    public static void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Telemetry.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new PropertiesManager();

        SmartDashboard.putBoolean(TelemetryNames.Telemetry.status, true);
    }

    public static PropertiesManager getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }
        return ourInstance;
    }

    private static String fileName = ""; // TODO - determine actual file name

    private static BufferedReader reader;
    private static Properties props;

    private PropertiesManager() {
        try {
            reader = new BufferedReader(new FileReader(fileName));

            props = new Properties();
            props.load(reader);

        } catch (FileNotFoundException ex) { // Possibly thrown by construction of BufferedReader
            logger.error("Can't create a reader from file {} because {} ", fileName, ex.getMessage());

        } catch (IOException ex) { // Possibly thrown from loading properties from the BufferedReader
            logger.error("Can't load properties from file {} because {}", fileName, ex.getMessage());

        }
    }

    public double getDouble(String key) {
        return Double.parseDouble(props.getProperty(key));
    }

    public int getInteger(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }

    public String getString(String key) {
        return props.getProperty(key);
    }

}
