package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
    	
    	String env = System.getProperty("environment");

        if (env == null) {
            env = "dev"; // default environment
        }
    	
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config/config-" + env + ".properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config-" + env + ".properties");
        }
    }

    public static String getProperty(String key) {
        return System.getProperty(key) != null
                ? System.getProperty(key)
                : prop.getProperty(key);
    }
}