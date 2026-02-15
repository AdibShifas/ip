package flores.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Deadline deadline = new Deadline("return book", "2026-02-15");
        assertEquals("[D][ ] return book (by: Feb 15 2026)", deadline.toString());
    }

    @Test
    public void testFileStringConversion() {
        Deadline deadline = new Deadline("return book", "2026-02-15");
        assertEquals("D | 0 | return book | 2026-02-15", deadline.toFileString());
    }

    @Test
    public void testMarkAsDone() {
        Deadline deadline = new Deadline("return book", "2026-02-15");
        deadline.markAsDone();
        assertEquals("[D][X] return book (by: Feb 15 2026)", deadline.toString());
        assertEquals("D | 1 | return book | 2026-02-15", deadline.toFileString());
    }
}
