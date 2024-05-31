import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;
import org.objectable.util.FileHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FileHandlerTest {


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }


    @Test
    void readFileAsStreamTest() throws IOException {
        assertInstanceOf(FileInputStream.class, FileHandler.getFileAsStream(Paths.get(
                Config.getProperty("TEST_RECORDS_DIRECTORY"),
                Config.getProperty("TEST_RECORDS_FILENAME")).toString()));
    }

    @Test
    void readLinesTest() {
        assertDoesNotThrow(() -> FileHandler.readLines(Paths.get(
                System.getProperty("user.dir"),
                Config.getProperty("TEST_RECORDS_DIRECTORY"),
                Config.getProperty("TEST_RECORDS_FILENAME"))));
    }
}
