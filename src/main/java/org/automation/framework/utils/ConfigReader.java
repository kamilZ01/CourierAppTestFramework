package org.automation.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        String resource = Paths.get(System.getProperty("user.dir"), ".env").toString();
        try (FileInputStream input = new FileInputStream(resource)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String getStringProperty(String key) {
        String value = System.getProperty(key, properties.getProperty(key));
        if (value == null)
            throw new IllegalArgumentException(String.format("Property [%s] not found in config.properties", key));

        return value;
    }

    public static int getNumberProperty(String key) {
        String value = getStringProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid format for [%s] parameter.", key));
        }
    }

}
