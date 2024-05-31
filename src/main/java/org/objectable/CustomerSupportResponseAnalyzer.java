package org.objectable;

import org.objectable.configuration.Config;
import org.objectable.util.Logger;
import org.objectable.util.RecordsListAnalyzer;
import org.objectable.util.validator.Validator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerSupportResponseAnalyzer {


    public static void main(String[] args) {
        Logger.info("START");
                Config.loadProperties();
        Path filePath = Paths.get(System.getProperty("user.dir"), Config.getProperty("RECORDS_DIRECTORY"), Config.getProperty("RECORDS_FILENAME"));
        if (args.length > 0 && Validator.isValidPath(args[0])) {
            filePath = Paths.get(args[0]);
            Config.setProperty("RECORDS_FILENAME", filePath.toAbsolutePath().toString());
        }
        RecordsListAnalyzer.analyzeFile(filePath);
        Logger.info("FINISH");
    }


    public static void terminate(String message) {
        Logger.error(message);
        Logger.info("TERMINATED");
        System.exit(1);
    }
}