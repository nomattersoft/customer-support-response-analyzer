package org.objectable.util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Logger {

    private static final String MESSAGES_DIRECTORY = "config";
    private static final String MESSAGES_FILENAME = "common.msg";
    private static Properties messages = new Properties();

    private static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";


    static {
        try {
            messages.load(FileHandler.getFileAsStream(Paths.get(MESSAGES_DIRECTORY, MESSAGES_FILENAME).toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void info(String placeholder) {
        print(placeholder, GREEN);
    }


    public static void error(String placeholder) {
        print(placeholder, RED);
    }


    private static void print(String placeholder, String color) {
        if (messages.getProperty(placeholder) == null) {
            System.out.println(color + placeholder + RESET);
        } else {
            System.out.println(color + messages.getProperty(placeholder) + RESET);
        }
    }


    public static String getMessage(String placeholder) {
        return messages.getProperty(placeholder);
    }
}
