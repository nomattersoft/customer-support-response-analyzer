package org.objectable;

import org.objectable.configuration.Config;

public class CustomerSupportResponseAnalyzer {

    public static void main(String[] args) {
        Config.loadProperties();
        System.out.printf("Args: %s%n", (args.length > 0 ? args[0] : Config.getProperty("DEFAULT_RECORDS_FILENAME")));
    }
}