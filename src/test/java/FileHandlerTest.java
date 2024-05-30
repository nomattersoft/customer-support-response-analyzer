import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;
import org.objectable.util.FileHandler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FileHandlerTest {


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }


    @Test
    void readRecordsListFileTest() {
        assertDoesNotThrow(() -> FileHandler.getFileInputStream(Config.getProperty("TEST_RECORDS_FILENAME")));
    }
}
