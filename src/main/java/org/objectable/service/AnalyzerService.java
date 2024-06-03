package org.objectable.service;

import org.objectable.configuration.Config;
import org.objectable.util.analizer.RecordsAnalyzer;
import org.objectable.util.handler.FileHandler;
import org.objectable.util.handler.QueryHandler;
import org.objectable.util.parser.RecordParser;
import org.objectable.util.validator.Validator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AnalyzerService {


    public void analyze(Optional<String> pathArgument) {
        Path filePath = Paths.get(Config.getHomeDirectory(), Config.getProperty("RECORDS_DIRECTORY"), Config.getProperty("RECORDS_FILENAME"));
        Validator validator = new Validator();
        if (pathArgument.isPresent() && validator.isValidPath(pathArgument.get())) {
            filePath = Paths.get(pathArgument.get());
            Config.setProperty("RECORDS_FILENAME", filePath.toAbsolutePath().toString());
        }
        RecordsAnalyzer analyzer = new RecordsAnalyzer(new FileHandler(), new RecordParser(), new QueryHandler());
        analyzer.analyzeFile(filePath);
    }
}
