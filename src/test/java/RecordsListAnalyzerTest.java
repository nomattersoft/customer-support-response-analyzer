import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;
import org.objectable.util.RecordsListAnalyzer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RecordsListAnalyzerTest {


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }

    @Test
    void analyzeTest() {
        List<String> records = Arrays.asList(
                "C 1.1 8.15.1 P 15.10.2012 83",
                "C 1 10.1 P 01.12.2012 65",
                "C 1.1 5.5.1 P 01.11.2012 117",
                "D 1.1 8 P 01.01.2012-01.12.2012",
                "C 3 10.2 N 02.10.2012 100",
                "D 1 * P 8.10.2012-20.11.2012",
                "D 3 10 P 01.12.2012"
        );
        assertDoesNotThrow(() -> RecordsListAnalyzer.analyze(records));
    }

}