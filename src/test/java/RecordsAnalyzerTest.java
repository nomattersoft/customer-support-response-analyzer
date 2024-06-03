import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;
import org.objectable.util.analizer.RecordsAnalyzer;
import org.objectable.util.handler.FileHandler;
import org.objectable.util.handler.QueryHandler;
import org.objectable.util.parser.RecordParser;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RecordsAnalyzerTest {

    private final RecordsAnalyzer analyzer = new RecordsAnalyzer(new FileHandler(), new RecordParser(), new QueryHandler());


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }

    @Test
    void analyzeFile() {
        analyzer.analyzeFile(Paths.get(
                Config.getHomeDirectory(),
                Config.getProperty("TEST_RECORDS_DIRECTORY"),
                Config.getProperty("TEST_RECORDS_FILENAME")));
    }

    @Test
    void analyzeListTest() {
        List<String> records = Arrays.asList(
                "C 1.1 8.15.1 P 15.10.2012 83",
                "C 1 10.1 P 01.12.2012 65",
                "C 1.1 5.5.1 P 01.11.2012 117",
                "D 1.1 8 P 01.01.2012-01.12.2012",
                "C 3 10.2 N 02.10.2012 100",
                "D 1 * P 8.10.2012-20.11.2012",
                "D 3 10 P 01.12.2012"
        );
        assertDoesNotThrow(() -> analyzer.analyzeList(records));
    }
}
