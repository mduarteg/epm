package epm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static Properties props;
    private static final String propertiesFile = "epm.properties";

    public static String getPropertyValue(String prop) {
        return getProperties().getProperty(prop);
    }

    private static Properties getProperties() {
        if (props == null) {
            loadProperties();
        }

        return props;
    }

    public static void loadProperties() {
        if (props == null) {
            props = new Properties();

            try {
                InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream(propertiesFile);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
