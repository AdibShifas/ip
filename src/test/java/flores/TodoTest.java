package flores;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        // Tests the visual output for the UI
        assertEquals("[T][ ] read book", new flores.task.Todo("read book").toString());
    }

    @Test
    public void testFileStringConversion() {
        // Tests the data format used for saving to the hard disk
        assertEquals("T | 0 | read book", new flores.task.Todo("read book").toFileString());
    }
}
