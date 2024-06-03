package org.objectable;

import org.objectable.configuration.Config;
import org.objectable.service.AnalyzerService;
import org.objectable.util.logging.Logger;

import java.util.Optional;

public class CustomerSupportResponseAnalyzer {

    private static final AnalyzerService analyzerService = new AnalyzerService();


    public static void main(String[] args) {
        Config.loadProperties();
        Logger.info("START");
        Optional<String> optionalPathArgument = Optional.empty();
        if (args.length > 0) {
            optionalPathArgument = Optional.of(args[0]);
        }
        analyzerService.analyze(optionalPathArgument);
        Logger.info("FINISH");
    }

    public static void terminate(String message) {
        Logger.error(message);
        Logger.info("TERMINATED");
        System.exit(1);
    }
}