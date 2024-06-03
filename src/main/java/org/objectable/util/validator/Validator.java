package org.objectable.util.validator;

import org.objectable.configuration.Config;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Validator {


    public static boolean isValidWaitingTimelineRecord(String recordString) {
        return recordPartMatches(recordString, "WAITING_TIMELINE_RECORD_PATTERN.regexp");
    }

    public static boolean isValidQueryRecord(String recordString) {
        return recordPartMatches(recordString, "QUERY_RECORD_PATTERN.regexp");
    }

    public static boolean isValidRecordSymbol(String recordPart) {
        return recordPartMatches(recordPart, "RECORD_SYMBOL_PATTERN.regexp");
    }

    public static boolean isServiceVariationPresent(String recordPart) {
        return recordPartMatches(recordPart, "SERVICE_WITH_VARIATION_PATTERN.regexp");
    }

    public static boolean isQuestionTypeCategoryPresent(String recordPart) {
        return recordPartMatches(recordPart, "QUESTION_TYPE_WITH_CATEGORY_PATTERN.regexp");
    }

    public static boolean isQuestionTypeSubcategoryPresent(String recordPart) {
        return recordPartMatches(recordPart, "QUESTION_TYPE_WITH_SUBCATEGORY_PATTERN.regexp");
    }

    public static boolean isValidDateRange(String recordPart) {
        return recordPartMatches(recordPart, "DATE_RANGE_FORMAT_PATTERN.regexp");
    }

    private static boolean recordPartMatches(String recordPart, String patternPropertyName) {
        return Pattern
                .compile(Config.getProperty(patternPropertyName))
                .matcher(recordPart.trim())
                .matches();
    }

    public static boolean isValidPath(String string) {
        try {
            Paths.get(string);
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
