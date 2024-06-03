package org.objectable.util.analizer;

import org.objectable.CustomerSupportResponseAnalyzer;
import org.objectable.configuration.Config;
import org.objectable.model.model.record.QueryRecord;
import org.objectable.model.model.record.WaitingTimelineRecord;
import org.objectable.util.handler.FileHandler;
import org.objectable.util.handler.QueryHandler;
import org.objectable.util.logging.Logger;
import org.objectable.util.parser.RecordParser;
import org.objectable.util.validator.Validator;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RecordsAnalyzer {

    private final List<WaitingTimelineRecord> waitingTimelineRecords = new ArrayList<>();
    private final List<QueryRecord> queryRecords = new ArrayList<>();

    private final RecordParser parser;
    private final QueryHandler queryHandler;
    private final FileHandler fileHandler;


    /**
     * Constructors
     */
    public RecordsAnalyzer(FileHandler fileHandler, RecordParser parser, QueryHandler queryHandler) {
        this.fileHandler = fileHandler;
        this.parser = parser;
        this.queryHandler = queryHandler;
    }

    /**
     * Analyzing methods
     */
    public void analyzeFile(Path fileName) {
        Logger.info(String.format(Config.getMessage("START_ANALYZING_FILE"), fileName));
        List<String> lines;
        try {
            lines = fileHandler.readLines(fileName);
            if (lines.isEmpty()) {
                CustomerSupportResponseAnalyzer.terminate(
                            Config.getMessage("NO_RECORDS_FOUND") +
                            Config.getMessage("CHECK_FILE_CONTENT"));
            }
            if (lines.size() > Config.getIntProperty("MAX_RECORD_COUNT")) {
                CustomerSupportResponseAnalyzer.terminate(
                            Config.getMessage("LINES_LIMIT_EXCEEDED") +
                            Config.getMessage("CHECK_FILE_CONTENT"));
            }
            try {
                int lineCount = Integer.parseInt(lines.remove(0).trim());
                if (lineCount != lines.size()) {
                    CustomerSupportResponseAnalyzer.terminate(String.format(
                                Config.getMessage("INVALID_LINES_NUMBER") +
                                Config.getMessage("CHECK_FILE_CONTENT"), lineCount, lines.size()));
                }
            } catch (NumberFormatException e) {
                CustomerSupportResponseAnalyzer.terminate(String.format(
                        Config.getMessage("INVALID_LINE_NUMBER")));
            }
            Logger.info(String.format(Config.getMessage("RECORDS_FOUND"), lines.size()));
            Logger.info("FINISH_ANALYZING_FILE");
            analyzeList(lines);
        } catch (IOException e) {
            CustomerSupportResponseAnalyzer.terminate(
                        Config.getMessage("NOT_FOUND_CORRUPTED_FILE") +
                        Config.getMessage("CHECK_FILE_LOCATION"));
        }
    }

    public void analyzeList(List<String> lines) {
        Logger.info("START_ANALYZING_LIST");
        Logger.info("RESULTS");
        for (int i = 0; i < lines.size(); i++) {
            if (Validator.isValidWaitingTimelineRecord(lines.get(i))) {
                try {
                    waitingTimelineRecords.add(parser.parseWaitingTimelineRecord(lines.get(i), i + 1));
                } catch (ParseException e) {
                    CustomerSupportResponseAnalyzer.terminate(Config.getMessage("RECORD_PARSING_FAILED"));
                }
            } else if (Validator.isValidQueryRecord(lines.get(i))) {
                try {
                    QueryRecord record = parser.parseQueryRecord(lines.get(i), i + 1);
                    queryRecords.add(record);
                    queryHandler.executeQuery(record, waitingTimelineRecords);
                } catch (ParseException e) {
                    CustomerSupportResponseAnalyzer.terminate(Config.getMessage("RECORD_PARSING_FAILED"));
                }
            } else {
                CustomerSupportResponseAnalyzer.terminate(
                        String.format(Config.getMessage("PARSE_OR_STRUCTURE_ERROR"), lines.get(i), i + 1));
            }
        }
        Logger.info(String.format(Config.getMessage("TIMELINE_SUCCESS"), waitingTimelineRecords.size()));
        Logger.info(String.format(Config.getMessage("QUERY_SUCCESS"), queryRecords.size()));
        Logger.info("FINISH_ANALYZING_LIST");
    }
}
