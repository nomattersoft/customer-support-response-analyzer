package org.objectable.util;

import org.objectable.configuration.Config;
import org.objectable.model.model.record.QueryRecord;
import org.objectable.model.model.record.WaitingTimelineRecord;
import org.objectable.util.parser.Parser;
import org.objectable.util.validator.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RecordsListAnalyzer {

    private static final List<WaitingTimelineRecord> waitingTimelineRecords = new ArrayList<>();


    public static void analyze(String fileName) throws IOException {
        List<String> lines = Parser.parseList(Config.getProperty(fileName));
        if (lines.isEmpty()) {
            System.err.printf("No record found in the file: %s\n", fileName);
//          TODO: probably wait and retry
//            System.err.printf("Check records file location (%s) or it content and run program again", );
        }

        if (lines.size() > Integer.parseInt(Config.getProperty("MAX_RECORD_COUNT"))) {
            System.out.printf("The limit of the number of lines (%d) in the file (%s) has been exceeded.\n", lines.size(), fileName);
//          TODO: probably wait and retry
//            System.err.printf("Check records file location (%s) or it content and run program again", );
        }

        int lineCount = Integer.parseInt(lines.remove(0).trim());
        if (lineCount != lines.size()) {
            System.err.printf("Invalid number of records found in the file (%s), specified %d but %d exists.\n", fileName, lineCount, lines.size());
//          TODO: prompt for farther processing
        }
    }

    public static void analyze(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (Validator.isValidWaitingTimelineRecord(lines.get(i))) {
                try {
                    waitingTimelineRecords.add(Parser.parseWaitingTimelineRecord(lines.get(i), i));
                } catch (ParseException e) {
                    System.err.printf("Record parsing failed:\n\trecord:\t\"%s\"\n\tline:\t%d\n", lines.get(i), i + 1);
                }
            } else if (Validator.isValidQueryRecord(lines.get(i))) {
                try {
                    QueryRecord record = Parser.parseQueryRecord(lines.get(i), i);
//                  TODO: execute query and show output
                    System.out.printf("Query Record found and parsed successfully:\n\trecord:\t\"%s\"\n\tline:\t%d\n", record, i + 1);
                } catch (ParseException e) {
                    System.err.printf("Record parsing failed:\n\trecord:\t\"%s\"\n\tline:\t%d\n", lines.get(i), i + 1);
                }
            } else {
                System.err.printf("Invalid record found:\n\trecord:\t\"%s\"\n\tline:\t%d", lines.get(i), i + 1);
//              TODO: prompt to continue analysis or break
            }
        }
        System.out.printf("Found and parsed successfully %d Waiting Timeline Records:\n\t%s\n", waitingTimelineRecords.size(), waitingTimelineRecords);
    }

}
