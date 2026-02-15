package flores.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import flores.exception.FloresException;

public class EventTest {

    @Test
    public void testStringConversion() throws FloresException {
        Event event = new Event("project meeting", "2026-02-16", "2026-02-17");
        assertEquals("[E][ ] project meeting (from: Feb 16 2026 to: Feb 17 2026)", event.toString());
    }

    @Test
    public void testFileStringConversion() throws FloresException {
        Event event = new Event("project meeting", "2026-02-16", "2026-02-17");
        assertEquals("E | 0 | project meeting | 2026-02-16 | 2026-02-17", event.toFileString());
    }

    @Test
    public void testInvalidDateOrder() {
        assertThrows(FloresException.class, () -> {
            new Event("time travel", "2026-02-17", "2026-02-16");
        });
    }

    @Test
    public void testMarkAsDone() throws FloresException {
        Event event = new Event("project meeting", "2026-02-16", "2026-02-17");
        event.markAsDone();
        assertEquals("[E][X] project meeting (from: Feb 16 2026 to: Feb 17 2026)", event.toString());
        assertEquals("E | 1 | project meeting | 2026-02-16 | 2026-02-17", event.toFileString());
    }
}
