/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class PropertiesManager {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PropertiesManager.class.getName());

    /* Default fully qualified file name */
    public static final String defaultFileName = "/home/lvuser/501robot.props";

    private static PropertiesManager ourInstance;

    private static String myName = "Props";

    public static void constructInstance() {
        constructInstance(defaultFileName);
    }

    public static void constructInstance(String fileName) {
        SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.unknown.tlmValue);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.inProgress.tlmValue);

        ourInstance = new PropertiesManager(fileName);

        // Put name of robot onto dashboard
        String robotName = ourInstance.getProperties(PropertyNames.Robot.name).getString("name");
        SmartDashboard.putString(TelemetryNames.Misc.robotName, robotName);

        SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.success.tlmValue);
    }

    public static PropertiesManager getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }
        return ourInstance;
    }

    private final Map<String, Map<String, String>> ownerProperties;

    private PropertiesManager(String fileName) {
        logger.info("constructing");

        logger.debug("file: {}", fileName);

        ownerProperties = new HashMap<String, Map<String, String>>();

        try {
            // Reader for properties file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // Use Properties class for conviencence to read & parse file
            Properties props = new Properties();
            props.load(reader);
            /* logger.trace("properties as read: {}", props); */

            Map<String, String> rawProperties = props.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
            Map<String, String> sortedProperties = rawProperties.entrySet().stream().sorted(comparingByKey())
                    .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
            logger.info("properties as read:\n{}", sortedProperties);

            props.forEach((k, v) -> sort(k, v));
        } catch (IOException ex) {
            logger.error("Can't load properties from file {}", fileName, ex);
            // FIXME - Need to handle this error and not continue
        }

        logger.info("constructed");
    }

    // Expression to parse "owner.property"
    private final Pattern pattern = Pattern.compile("\\.");

    /**
     * 
     * @param arg0 - key of set
     * @param arg1 - value of set (don't care but need for method)
     */
    private void sort(Object arg0, Object arg1) {
        // Cast arguments into strings
        String key = (String) arg0;
        String value = (String) arg1;
        // Splits "argument" into its subsystem and property parts
        String[] keys = pattern.split(key);
        // Assigns subsystem and property keys
        String ownerKey = keys[0];
        String propKey = keys[1];
        // Check if the subsystem-organized array already contains an entry for the
        // subsystem
        if (!(ownerProperties.containsKey(ownerKey))) {
            // If it doesn't, create one
            ownerProperties.put(ownerKey, new HashMap<String, String>());
        }
        // Now there has to be a subsystem entry, so we assign the values into that
        // subsystem's corresponding PKProperties Object
        ownerProperties.get(ownerKey).put(propKey, value);
    }

    public PKProperties getProperties(String owner) {
        if (ownerProperties.containsKey(owner)) {
            return new PKProperties(owner, ownerProperties.get(owner));
        } else {
            logger.error("Properties for owner {} don't exist");
            return new PKProperties(owner, new HashMap<String, String>());
        }
    }

    public void listProperties() {
        for (String owner : ownerProperties.keySet()) {
            getProperties(owner).listProperties();
        }
    }

}
