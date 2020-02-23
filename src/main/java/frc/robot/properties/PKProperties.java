/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.properties;

import java.util.Map;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class PKProperties {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PKProperties.class.getName());

    // "Owner" of the properties (mostly for logging)
    private final String owner;
    // Map of the properties
    private final Map<String, String> props;

    public PKProperties(String owner, Map<String, String> props) {
        this.owner = owner;
        this.props = props;
    }

    public double getDouble(String key) {
        String value = getProperty(key);
        double retValue;
        try {
            retValue = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            logger.error("{}'s property key={} value={} fails to parse", owner, key, value, ex);
            retValue = 0.0;
        }
        return retValue;
    }

    public long getLong(String key) {
        String value = getProperty(key);
        long retValue;
        try {
            retValue = Long.parseLong(value);
        } catch (NumberFormatException ex) {
            logger.error("{}'s property key={} value={} fails to parse", owner, key, value, ex);
            retValue = 0;
        }
        return retValue;
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    public String getString(String key) {
        return getProperty(key);
    }

    /**
     * Ensures that a missing property doesn't blow up.
     * 
     * @param key
     * @return
     */
    private String getProperty(String key) {
        String value = props.get(key);
        if ((value == null) || value.isEmpty()) {
            logger.error("{}'s property key={} is not defined", owner, key);
            value = "";
        }
        return value;
    }

    public void listProperties() {
        StringBuilder buf = new StringBuilder();
        buf.append("owner ").append(owner).append(" properties:");
        for (String key : props.keySet()) {
            buf.append("\n..."); // logger gobbles up leading spaces
            buf.append(key).append(" = ").append(props.get(key));
        }
        logger.info(buf.toString());
    }

}
