package flores; // Standard package name for the test source set

import flores.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        // Tests the visual output for the UI
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }

    @Test
    public void testFileStringConversion() {
        // Tests the data format used for saving to the hard disk
        assertEquals("T | 0 | read book", new Todo("read book").toFileString());
    }
}