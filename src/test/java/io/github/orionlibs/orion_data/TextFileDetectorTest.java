package io.github.orionlibs.orion_data;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
//@Execution(ExecutionMode.CONCURRENT)
public class TextFileDetectorTest extends ATest
{
    @Test
    void test_isTextFile_true() throws IOException
    {
        boolean result = TextFileDetector.isTextFile(loadResourceAsStream("/io/github/orionlibs/orion_data/text-only.txt"));
        assertTrue(result);
    }


    @Test
    void test_isTextFile_false() throws IOException
    {
        boolean result = TextFileDetector.isTextFile(loadResourceAsStream("/io/github/orionlibs/orion_data/binary"));
        assertFalse(result);
    }
}
