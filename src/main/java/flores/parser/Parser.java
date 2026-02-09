package flores.parser;

import flores.exception.FloresException;

/**
 * Handles the interpretation of user input.
 * This class parses raw input strings to identify commands and extract specific
 * details required for task operations.
 */
public class Parser {

    private static final String TODO_CMD = "todo";
    private static final String DEADLINE_CMD = "deadline";
    private static final String EVENT_CMD = "event";
    private static final String FIND_CMD = "find";
    private static final String BY_KEYWORD = " /by ";
    private static final String FROM_KEYWORD = " /from ";
    private static final String TO_KEYWORD = " /to ";

    /**
     * Parses the first word of the user input to determine the command type.
     *
     * @param input The full raw input string from the user.
     * @return The corresponding {@code Command} enum, or {@code Command.UNKNOWN} if
     *         not recognized.
     */
    public static Command getCommand(String input) {
        assert input != null : "Input cannot be null";
        String cmd = input.split(" ")[0].toUpperCase();
        try {
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /**
     * Extracts the description for a Todo task from the input string.
     *
     * @param input The raw input string (e.g., "todo read book").
     * @return The trimmed description of the task.
     * @throws FloresException If the description is empty.
     */
    public static String getTodoDescription(String input) throws FloresException {
        assert input.toLowerCase().startsWith(TODO_CMD) : "Input must start with '" + TODO_CMD + "'";
        if (input.trim().equalsIgnoreCase(TODO_CMD)) {
            throw new FloresException("The description of a todo cannot be empty.");
        }
        return input.substring(TODO_CMD.length() + 1).trim();
    }

    /**
     * Extracts the description and deadline time for a Deadline task.
     *
     * @param input The raw input string (e.g., "deadline return book /by Monday").
     * @return A String array where index 0 is the description and index 1 is the
     *         deadline time.
     * @throws FloresException If the description is empty or the "/by" keyword is
     *                         missing.
     */
    public static String[] getDeadlineData(String input) throws FloresException {
        assert input.toLowerCase().contains(DEADLINE_CMD) : "Input must contain '" + DEADLINE_CMD + "'";
        assert input.contains(BY_KEYWORD) : "Input must contain '" + BY_KEYWORD + "'";
        if (input.trim().equalsIgnoreCase(DEADLINE_CMD)) {
            throw new FloresException("The description of a deadline cannot be empty.");
        }
        if (!input.contains(BY_KEYWORD)) {
            throw new FloresException("A deadline must have a /by time.");
        }
        return input.substring(DEADLINE_CMD.length() + 1).split(BY_KEYWORD);
    }

    /**
     * Extracts the description, start time, and end time for an Event task.
     *
     * @param input The raw input string (e.g., "event meeting /from 2pm /to 4pm").
     * @return A String array where index 0 is the description, index 1 is the start
     *         time, and index 2 is the end time.
     * @throws FloresException If the description is empty or the "/from" or "/to"
     *                         keywords are missing.
     */
    public static String[] getEventData(String input) throws FloresException {
        assert input.toLowerCase().contains(EVENT_CMD) : "Input must contain '" + EVENT_CMD + "'";
        assert input.contains(FROM_KEYWORD) : "Input must contain '" + FROM_KEYWORD + "'";
        assert input.contains(TO_KEYWORD) : "Input must contain '" + TO_KEYWORD + "'";
        if (input.trim().equalsIgnoreCase(EVENT_CMD)) {
            throw new FloresException("The description of an event cannot be empty.");
        }
        if (!input.contains(FROM_KEYWORD) || !input.contains(TO_KEYWORD)) {
            throw new FloresException("An event must have /from and /to times.");
        }

        // Input: event project meeting /from 2026-01-28 /to 2026-01-29
        String content = input.substring(EVENT_CMD.length() + 1); // remove "event "
        String[] eventParts = content.split(FROM_KEYWORD);
        String description = eventParts[0];
        String[] timeParts = eventParts[1].split(TO_KEYWORD);

        // Returns {description, from, to}
        return new String[] { description, timeParts[0], timeParts[1] };
    }

    /**
     * Extracts the task index from the user input for commands like mark, unmark,
     * or delete.
     * Converts the user's 1-based index into a 0-based index for internal list
     * access.
     *
     * @param input       The full raw input string.
     * @param commandType The command keyword (e.g., "delete") used to determine the
     *                    substring offset.
     * @return The 0-based index of the target task.
     * @throws NumberFormatException If the provided index is not a valid integer.
     */
    public static int getIndex(String input, String commandType) {
        assert commandType != null : "Command type cannot be null";
        // commandType would be "mark", "unmark", or "delete"
        return Integer.parseInt(input.substring(commandType.length()).trim()) - 1;
    }

    /**
     * Extracts the search keyword from the find command input.
     *
     * @param input The raw user input (e.g., "find book").
     * @return The keyword to search for.
     * @throws FloresException If the keyword is missing.
     */
    public static String getFindKeyword(String input) throws FloresException {
        if (input.trim().equalsIgnoreCase(FIND_CMD)) {
            throw new FloresException("What am I supposed to find? Give me a keyword!");
        }
        return input.substring(5).trim();
    }
}