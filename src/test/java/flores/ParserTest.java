package flores;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import flores.exception.FloresException;
import flores.parser.Command;
import flores.parser.Parser;

public class ParserTest {
    @Test
    public void getDeadlineData_validInput_success() throws FloresException {
        String input = "deadline return book /by 2026-01-28";
        String[] expected = { "return book", "2026-01-28" };
        assertArrayEquals(expected, Parser.getDeadlineData(input));
    }

    @Test
    public void getDeadlineData_missingBy_exceptionThrown() {
        String input = "deadline return book 2026-01-28";
        assertThrows(FloresException.class, () -> Parser.getDeadlineData(input));
    }

    // Gemini: Generated test case to verify correct parsing.
    @Test
    public void getTodoDescription_validInput_success() throws FloresException {
        String input = "todo read book";
        String expected = "read book";
        assertEquals(expected, Parser.getTodoDescription(input));
    }

    // Gemini: Added boundary test for robust error handling.
    @Test
    public void getTodoDescription_emptyDescription_exceptionThrown() {
        String input = "todo";
        assertThrows(FloresException.class, () -> Parser.getTodoDescription(input));
    }

    // Gemini: Generated test case to verify composite data parsing.
    @Test
    public void getEventData_validInput_success() throws FloresException {
        String input = "event concert /from 18:00 /to 22:00";
        String[] expected = { "concert", "18:00", "22:00" };
        assertArrayEquals(expected, Parser.getEventData(input));
    }

    // Gemini: Added prompt error detection test.
    @Test
    public void getEventData_missingParts_exceptionThrown() {
        String input = "event concert /from 18:00";
        assertThrows(FloresException.class, () -> Parser.getEventData(input));
    }

    // Gemini: Validated command enum mapping with this test.
    @Test
    public void getCommand_validInput_success() {
        String input = "list tasks";
        assertEquals(Command.LIST, Parser.getCommand(input));
    }

    // Gemini: Added test to ensure graceful handling of invalid input.
    @Test
    public void getCommand_unknownInput_unknownCommand() {
        String input = "unknown command";
        assertEquals(Command.UNKNOWN, Parser.getCommand(input));
    }
}
