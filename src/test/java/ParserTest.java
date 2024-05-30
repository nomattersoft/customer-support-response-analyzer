import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;
import org.objectable.model.model.record.WaitingTimelineRecord;
import org.objectable.util.parser.Parser;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }


    @Test
    void parseListTest() throws IOException {
        assertEquals(8, Parser.parseList(Config.getProperty("TEST_RECORDS_FILENAME")).size());
    }

    @Test
    void parseWaitingTimelineRecordTest() throws ParseException {
        int recordIndex = 1;
        String record = "C 1.1 8.15.1 P 15.10.2012 83";
        assertInstanceOf(WaitingTimelineRecord.class, Parser.parseWaitingTimelineRecord(record, recordIndex));
    }
}
