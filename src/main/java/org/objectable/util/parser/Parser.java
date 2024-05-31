package org.objectable.util.parser;

import org.objectable.configuration.Config;
import org.objectable.model.model.*;
import org.objectable.model.model.record.QueryRecord;
import org.objectable.model.model.record.WaitingTimelineRecord;
import org.objectable.util.validator.Validator;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Parser {


    public static WaitingTimelineRecord parseWaitingTimelineRecord(String recordLine, Integer recordIndex) throws ParseException {
        List<String> recordLineParts = Arrays.stream(recordLine.split(" ")).toList();
        return new WaitingTimelineRecord(
                recordIndex,
                recordLineParts.get(Config.getIntProperty("RECORD_SYMBOL_PART_INDEX")),
                parseService(recordLineParts.get(Config.getIntProperty("SERVICE_PART_INDEX"))),
                parseQuestion(recordLineParts.get(Config.getIntProperty("QUESTION_PART_INDEX"))),
                recordLineParts.get(Config.getIntProperty("RESPONSE_TYPE_PART_INDEX")),
                parseDate(recordLineParts.get(Config.getIntProperty("DATE_PART_INDEX"))),
                Integer.parseInt(recordLineParts.get(Config.getIntProperty("TIME_PART_INDEX"))));
    }


    public static QueryRecord parseQueryRecord(String recordLine, Integer recordIndex) throws ParseException {
        List<String> recordLineParts = Arrays.stream(recordLine.split(" ")).toList();
        LocalDate dateTo = null;
        if (Validator.isDateRange(recordLineParts.get(Config.getIntProperty("DATE_PART_INDEX")))) {
            dateTo = parseDate(recordLineParts
                        .get(Config.getIntProperty("DATE_PART_INDEX"))
                        .split(Config.getProperty("DATE_RANGE_SPLITTER_SYMBOL"))[1]);
        }
        LocalDate dateFrom = parseDate(recordLineParts
                            .get(Config.getIntProperty("DATE_PART_INDEX"))
                            .split(Config.getProperty("DATE_RANGE_SPLITTER_SYMBOL"))[0]);
        return new QueryRecord(
                recordIndex,
                recordLineParts.get(Config.getIntProperty("RECORD_SYMBOL_PART_INDEX")),
                parseService(recordLineParts.get(Config.getIntProperty("SERVICE_PART_INDEX"))),
                parseQuestion(recordLineParts.get(Config.getIntProperty("QUESTION_PART_INDEX"))),
                recordLineParts.get(Config.getIntProperty("RESPONSE_TYPE_PART_INDEX")),
                dateFrom,
                dateTo);
    }


    public static Service parseService(String recordLinePart) {
        if (recordLinePart.equals(Config.getProperty("ID_WILDCARD_SYMBOL"))) return null;
        if (Validator.isServiceVariationPresent(recordLinePart)) {
            String[] splitLinePart = recordLinePart.split("\\.");
            return new Service(
                    Integer.parseInt(splitLinePart[0]),
                    new ServiceVariation(Integer.parseInt(splitLinePart[1])));
        }
        return new Service(Integer.parseInt(recordLinePart));
    }


    public static QuestionType parseQuestion(String recordLinePart) {
        if (recordLinePart.equals(Config.getProperty("ID_WILDCARD_SYMBOL"))) return null;
        if (Validator.isQuestionTypeSubcategoryPresent(recordLinePart)) {
            String[] splitLinePart = recordLinePart.split("\\.");
            return new QuestionType(
                    Integer.parseInt(splitLinePart[0]),
                    new QuestionCategory(Integer.parseInt(splitLinePart[1]),
                    new QuestionSubcategory(Integer.parseInt(splitLinePart[2]))));
        } else if (Validator.isQuestionTypeCategoryPresent(recordLinePart)) {
            String[] splitLinePart = recordLinePart.split("\\.");
            return new QuestionType(
                    Integer.parseInt(splitLinePart[0]),
                    new QuestionCategory(Integer.parseInt(splitLinePart[1])));
        }
        return new QuestionType(Integer.parseInt(recordLinePart));
    }


    public static LocalDate parseDate(String recordLinePart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Config.getProperty("DATE_FORMAT_PATTERN"));
        return LocalDate.parse(recordLinePart, formatter);
    }
}
