package org.objectable.configuration;

import org.objectable.util.FileHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FILENAME = "application.properties";

    private static SimpleDateFormat dateFormat;

    private static Properties properties = new Properties();


    public static void loadProperties() {
        try {
            properties.load(FileHandler.getFileInputStream(PROPERTIES_FILENAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dateFormat = new SimpleDateFormat(Config.getProperty("DATE_FORMAT_PATTERN"));
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Properties getProperties() {
        return properties;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
}
