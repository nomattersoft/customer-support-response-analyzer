package org.objectable.util.validator;

import org.objectable.configuration.Config;

import java.util.regex.Pattern;

public class Validator {


    public static boolean isRecordValid(String recordString) {
        return isValidQueryRecord(recordString) || isValidWaitingTimelineRecord(recordString);
    }


    public static boolean isValidWaitingTimelineRecord(String recordString) {
        return matches(recordString, "WAITING_TIMELINE_RECORD_PATTERN.regexp");
    }


    public static boolean isValidQueryRecord(String recordString) {
        return matches(recordString, "QUERY_RECORD_PATTERN.regexp");
    }


    public static boolean isValidRecordSymbol(String recordPart) {
        return matches(recordPart, "RECORD_SYMBOL_PATTERN.regexp");
    }


    public static boolean isServiceVariationPresent(String recordPart) {
        return matches(recordPart, "SERVICE_WITH_VARIATION_PATTERN.regexp");
    }


    public static boolean isQuestionTypeCategoryPresent(String recordPart) {
        return matches(recordPart, "QUESTION_TYPE_WITH_CATEGORY_PATTERN.regexp");
    }


    public static boolean isQuestionTypeSubcategoryPresent(String recordPart) {
        return matches(recordPart, "QUESTION_TYPE_WITH_SUBCATEGORY_PATTERN.regexp");
    }


    public static boolean isDateRange(String recordPart) {
        return matches(recordPart, "DATE_RANGE_FORMAT_PATTERN.regexp");
    }


    public static boolean matches(String recordPart, String patternPropertyName) {
        return Pattern
                .compile(Config.getProperty(patternPropertyName))
                .matcher(recordPart.trim())
                .matches();
    }
}
