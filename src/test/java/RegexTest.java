import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexTest {


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }

    @Test
    void waitingTimelineRecordPatternTest() {
        String record = "C 1.1 8.15.1 P 15.12.2012 83";
        assertTrue(Pattern
                .compile(Config.getProperty("WAITING_TIMELINE_RECORD_PATTERN.regexp"))
                .matcher(record)
                .matches());
    }

    @Test
    void queryRecordPatternTest() {
        String record = "D * * P 01.12.2012-20.11.2013";
        assertTrue(Pattern
                .compile(Config.getProperty("QUERY_RECORD_PATTERN.regexp"))
                .matcher(record)
                .matches());
    }

    @Test
    void recordTypeSymbolPatternTest() {
        String record = "C";
        assertTrue(Pattern
                .compile(Config.getProperty("RECORD_SYMBOL_PATTERN.regexp"))
                .matcher(record)
                .matches());
    }

    @Test
    void serviceWithVariationPatternTest() {
        String recordPart = "1.1";
        assertTrue(Pattern
                .compile(Config.getProperty("SERVICE_WITH_VARIATION_PATTERN.regexp"))
                .matcher(recordPart)
                .matches());
    }

    @Test
    void questionTypeWithCategoryPatternTest() {
        String recordPart = "8.15";
        assertTrue(Pattern
                .compile(Config.getProperty("QUESTION_TYPE_WITH_CATEGORY_PATTERN.regexp"))
                .matcher(recordPart)
                .matches());
    }

    @Test
    void questionTypeWithSubcategoryPatternTest() {
        String recordPart = "8.15.1";
        assertTrue(Pattern
                .compile(Config.getProperty("QUESTION_TYPE_WITH_SUBCATEGORY_PATTERN.regexp"))
                .matcher(recordPart)
                .matches());
    }

    @Test
    void responseTypeSymbolPatternTest() {
        String record = "P";
        assertTrue(Pattern
                .compile(Config.getProperty("RESPONSE_TYPE_SYMBOL_PATTERN.regexp"))
                .matcher(record)
                .matches());
    }

    @Test
    void datePatternTest() {
        String record = "21.12.2012";
        assertTrue(Pattern
                .compile(Config.getProperty("DATE_FORMAT_PATTERN.regexp"))
                .matcher(record)
                .matches());
    }

    @Test
    void dateRangePatternTest() {
        String record = "8.10.2012-20.11.2012";
        assertTrue(Pattern
                .compile(Config.getProperty("DATE_RANGE_FORMAT_PATTERN.regexp"))
                .matcher(record)
                .matches());
    }
}
