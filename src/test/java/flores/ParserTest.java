package flores;

import flores.exception.FloresException;
import flores.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void getDeadlineData_validInput_success() throws FloresException {
        String input = "deadline return book /by 2026-01-28";
        String[] expected = {"return book", "2026-01-28"};
        assertArrayEquals(expected, Parser.getDeadlineData(input));
    }

    @Test
    public void getDeadlineData_missingBy_exceptionThrown() {
        String input = "deadline return book 2026-01-28";
        assertThrows(FloresException.class, () -> Parser.getDeadlineData(input));
    }
}