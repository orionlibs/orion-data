package io.github.orionlibs.orion_data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.orionlibs.orion_tuple.Pair;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
//@Execution(ExecutionMode.CONCURRENT)
public class InifileTest extends ATest
{
    @Test
    void test_load() throws IOException
    {
        Map<String, Set<Pair<String, String>>> config = IniFile.load(loadResourceAsStream("/io/github/orionlibs/orion_data/configuration.ini"));
        assertTrue(config.get("WebSites").contains(Pair.of("VbTips", "http://www.vb-helper.com/whats_new.html")));
        assertTrue(config.get("WebSites").contains(Pair.of("Comic", "http://www.userfriendly.org/")));
        assertTrue(config.get("Directories").contains(Pair.of("Image", "C:\\RBP Project\\Pictures")));
        assertTrue(config.get("Directories").contains(Pair.of("Data", "C:\\RBP Project\\DB")));
    }
}
