import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;
import org.objectable.model.model.record.QueryRecord;
import org.objectable.model.model.record.WaitingTimelineRecord;
import org.objectable.util.handler.FileHandler;
import org.objectable.util.logging.Logger;
import org.objectable.util.parser.RecordParser;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class RecordsParserTest {

    private final RecordParser parser = new RecordParser();
    private final FileHandler fileHandler = new FileHandler();


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }

    @Test
    void parseListTest() throws IOException {
        assertEquals(8, fileHandler.readLines(Paths.get(
                System.getProperty("user.dir"),
                Config.getProperty("TEST_RECORDS_DIRECTORY"),
                Config.getProperty("TEST_RECORDS_FILENAME"))).size());
    }

    @Test
    void parseWaitingTimelineRecordTest() throws ParseException {
        int recordIndex = 1;
        String record = "C 1.1 8.15.1 P 15.10.2012 83";
        assertInstanceOf(WaitingTimelineRecord.class, parser.parseWaitingTimelineRecord(record, recordIndex));
    }

    @Test
    void parseQueryRecord() throws ParseException {
        int recordIndex = 1;
        String record = "D 1 * P 8.10.2012-20.11.2012";
        QueryRecord queryRecord = parser.parseQueryRecord(record, recordIndex);
        Logger.pure(String.format("%s", queryRecord));
        assertInstanceOf(QueryRecord.class, queryRecord);
    }
}
