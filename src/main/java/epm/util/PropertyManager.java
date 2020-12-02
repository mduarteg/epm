package epm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {
    private static Properties props;
    private static Map<String, String> emProps;
    private static final String propertiesFile = "epm.properties";

    public static String getPropertyValue(String prop) {
        return getProperties().getProperty(prop);
    }

    public static int getPropertyIntValue(String prop) {
        return Integer.parseInt(getProperties().getProperty(prop));
    }

    private static Properties getProperties() {
        if (props == null) {
            loadProperties();
        }

        return props;
    }

    public static Map<String, String> getEmProperties() {
        if (emProps == null) {
            loadProperties();
        }

        return emProps;
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

            loadEmProperties();
        }
    }

    private static void loadEmProperties() {
        emProps = new HashMap<>();

        emProps.put("javax.persistence.jdbc.url", "jdbc:postgresql://127.0.0.1:5432/" + props.getProperty("db.service"));
        emProps.put("javax.persistence.jdbc.user", props.getProperty("db.user"));
        emProps.put("javax.persistence.jdbc.password", props.getProperty("db.password"));
    }
}
