package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadProperties() {

        String env = System.getProperty("environment");

        if (env == null) {
            env = "dev"; // default environment
        }

        try {
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config/config-" + env + ".properties");

            properties = new Properties();
            properties.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
