package org.objectable.util;

import org.objectable.CustomerSupportResponseAnalyzer;
import org.objectable.configuration.Config;
import org.objectable.model.model.record.QueryRecord;
import org.objectable.model.model.record.WaitingTimelineRecord;
import org.objectable.util.parser.Parser;
import org.objectable.util.validator.Validator;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RecordsListAnalyzer {

    private static final List<WaitingTimelineRecord> waitingTimelineRecords = new ArrayList<>();
    private static final List<QueryRecord> queryRecords = new ArrayList<>();


    public static void analyzeFile(Path fileName) {
        Logger.info(String.format("Start analyzing file: %s", fileName));
        List<String> lines;
        try {
            lines = FileHandler.readLines(fileName);
            if (lines.isEmpty()) {
                CustomerSupportResponseAnalyzer.terminate(String.format(
                            Logger.getMessage("NO_RECORDS_FOUND") + "%s\n" +
                            Logger.getMessage("CHECK_FILE_CONTENT"), fileName));
            }
            if (lines.size() > Config.getIntProperty("MAX_RECORD_COUNT")) {
                CustomerSupportResponseAnalyzer.terminate(String.format(
                            Logger.getMessage("LINES_LIMIT_EXCEEDED") + "%s\n" +
                            Logger.getMessage("CHECK_FILE_CONTENT"),
                            Config.getIntProperty("MAX_RECORD_COUNT"), fileName));
            }
            try {
                int lineCount = Integer.parseInt(lines.remove(0).trim());
                if (lineCount != lines.size()) {
                    CustomerSupportResponseAnalyzer.terminate(String.format(
                                Logger.getMessage("INVALID_LINES_NUMBER") + "%s\n" +
                                        "specified: %d\n" +
                                        "exists: %d\n" +
                                        Logger.getMessage("CHECK_FILE_CONTENT"),
                                        fileName, lineCount, lines.size()));
                }
            } catch (NumberFormatException e) {
                CustomerSupportResponseAnalyzer.terminate(String.format(
                        Logger.getMessage("INVALID_LINE_NUMBER") + "%s", fileName));
            }
            Logger.info(String.format("Found %d records in file: %s", lines.size(), fileName));
            Logger.info("FINISH_ANALYZING_FILE");
            analyze(lines);
        } catch (IOException e) {
            CustomerSupportResponseAnalyzer.terminate(String.format(
                        Logger.getMessage("NOT_FOUND_CORRUPTED_FILE") + "%s\n" +
                        Logger.getMessage("CHECK_FILE_LOCATION"), fileName));
        }
    }


    public static void analyze(List<String> lines) {
        Logger.info("START_ANALYZING_LIST");
        Logger.info("RESULTS");
        long skippedTimelineRecordCount = 0;
        long skippedQueryRecordCount = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (Validator.isValidWaitingTimelineRecord(lines.get(i))) {
                try {
                    waitingTimelineRecords.add(Parser.parseWaitingTimelineRecord(lines.get(i), i));
                } catch (ParseException e) {
                    if (!Config.getBooleanProperty("SKIP_INVALID_RECORDS")) {
                        CustomerSupportResponseAnalyzer.terminate(String.format(
                                Logger.getMessage("TIMELINE_PARSING_FAILED") +
                                "\n\trecord: \"%s\"\n" +
                                "\tline: %d\n",
                                lines.get(i), i + 1));
                    }
                    skippedTimelineRecordCount++;
                }
            } else if (Validator.isValidQueryRecord(lines.get(i))) {
                try {
                    QueryRecord record = Parser.parseQueryRecord(lines.get(i), i);
                    queryRecords.add(record);
                    QueryHandler.executeQuery(record, waitingTimelineRecords);
                } catch (ParseException e) {
                    if (!Config.getBooleanProperty("SKIP_INVALID_RECORDS")) {
                        CustomerSupportResponseAnalyzer.terminate(String.format(
                                Logger.getMessage("QUERY_PARSING_FAILED") +
                                "\n\trecord: \"%s\"\n" +
                                "\tline: %d\n",
                                lines.get(i), i + 1));
                    }
                    skippedQueryRecordCount++;
                }
            }
        }
        Logger.info(String.format(Logger.getMessage("TIMELINE_SUCCESS") + "%d\n" +
                "\tSkipped: %d", waitingTimelineRecords.size(), skippedTimelineRecordCount));
        Logger.info(String.format(Logger.getMessage("QUERY_SUCCESS") + "%d\n" +
                "\tSkipped: %d", queryRecords.size(), skippedQueryRecordCount));
        Logger.info("FINISH_ANALYZING_LIST");
    }

}
