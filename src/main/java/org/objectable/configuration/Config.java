package org.objectable.configuration;

import org.objectable.CustomerSupportResponseAnalyzer;
import org.objectable.util.handler.FileHandler;
import org.objectable.util.logging.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_DIRECTORY = "config";
    private static final String PROPERTIES_FILENAME = "application.properties";
    private static final String MESSAGES_DIRECTORY = "config";
    private static final String MESSAGES_FILENAME = "common.msg";

    private static final Properties properties = new Properties();
    private static final Properties messages = new Properties();
    private static final FileHandler fileHandler = new FileHandler();


    public static void loadProperties() {
        try {
            messages.load(fileHandler.getFileAsStream(Paths.get(
                    MESSAGES_DIRECTORY,
                    MESSAGES_FILENAME).toString()));
            properties.load(fileHandler.getFileAsStream(Paths.get(
                    PROPERTIES_DIRECTORY,
                    PROPERTIES_FILENAME).toString()));
            Logger.info(getMessage("PROPERTIES_LOADED"));
        } catch (IOException | NullPointerException e) {
            CustomerSupportResponseAnalyzer.terminate(String.format(
                    getMessage("PROPERTIES_NOT_LOADED"),
                    PROPERTIES_FILENAME));
        }
    }

    /**
     * Getters & Setters
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getMessage(String key) {
        return messages.getProperty(key);
    }

    public static String getHomeDirectory() {
        return System.getProperty("user.dir");
    }
}
