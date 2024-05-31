import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectable.configuration.Config;

public class ConfigTest {


    @BeforeAll
    static void loadPropertiesTest() {
        Config.loadProperties();
    }

    @Test
    void propertiesNotEmptyTest() {
        assertFalse(Config.getProperties().isEmpty());
    }

    @Test
    void getPropertyTest() {
        assertEquals("test-records.txt", Config.getProperty("TEST_RECORDS_FILENAME"));
    }
}
