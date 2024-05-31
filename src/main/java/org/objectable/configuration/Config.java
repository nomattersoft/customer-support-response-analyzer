package org.objectable.configuration;

import org.objectable.CustomerSupportResponseAnalyzer;
import org.objectable.util.FileHandler;
import org.objectable.util.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FOLDER_NAME = "config";

    private static final String PROPERTIES_FILENAME = "application.properties";

    private static final String LOGGING_PROPERTIES_FILENAME = "logging.properties";

    private static SimpleDateFormat dateFormat;

    private static final Properties properties = new Properties();


    public static void loadProperties() {
        try {
            properties.load(FileHandler.getFileAsStream(Paths.get(
                    PROPERTIES_FOLDER_NAME,
                    PROPERTIES_FILENAME).toString()));
            Logger.info("PROPERTIES_LOADED");
        } catch (IOException | NullPointerException e) {
            CustomerSupportResponseAnalyzer.terminate(String.format(
                    "Could not load properties file: %s\n",
                    PROPERTIES_FILENAME));
        }
        dateFormat = new SimpleDateFormat(Config.getProperty("DATE_FORMAT_PATTERN"));
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }


    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }


    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }


    public static Properties getProperties() {
        return properties;
    }


    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
}
