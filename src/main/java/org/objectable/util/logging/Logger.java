package org.objectable.util.logging;

import org.objectable.configuration.Config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String RESET = "\033[0m";


    public static void info(String placeholder) {
        print("INFO", placeholder, GREEN);
    }

    public static void error(String placeholder) {
        print("ERROR", placeholder, RED);
    }

    public static void pure(String message) {
        System.out.println(message);
    }


    private static void print(String logLevel, String placeholder, String color) {
        String message;
        if (Config.getMessage(placeholder) == null) {
            message = placeholder;
        } else {
            message = Config.getMessage(placeholder);
        }
        System.out.printf("%s%s\t%s: %s%s%s", color, LocalDateTime.now().format(formatter), logLevel, message, RESET, "\n");
    }
}
