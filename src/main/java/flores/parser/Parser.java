package flores.parser;

import flores.exception.FloresException;

/**
 * Deals with making sense of the user command.
 * This class provides static methods to parse raw input strings into
 * command types and extract specific data for tasks.
 */
public class Parser {

    /**
     * Parses the first word of the input string to determine the command type.
     * * @param input The full raw input string from the user.
     *
     * @return The corresponding Command enum, or Command.UNKNOWN if the input is invalid.
     */
    public static Command getCommand(String input) {
        String cmd = input.split(" ")[0].toUpperCase();
        try {
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /**
     * Extracts the description for a Todo task from the input.
     * * @param input The raw input string (e.g., "todo read book").
     *
     * @return The description of the todo.
     * @throws FloresException If the description is empty.
     */
    public static String getTodoDescription(String input) throws FloresException {
        if (input.trim().equalsIgnoreCase("todo")) {
            throw new FloresException("The description of a todo cannot be empty.");
        }
        return input.substring(5).trim();
    }

    /**
     * Extracts the description and deadline date from the input.
     * * @param input The raw input string (e.g., "deadline return book /by 2026-01-28").
     *
     * @return A String array where index 0 is the description and index 1 is the date string.
     * @throws FloresException If the description is empty or the /by keyword is missing.
     */
    public static String[] getDeadlineData(String input) throws FloresException {
        if (input.trim().equalsIgnoreCase("deadline")) {
            throw new FloresException("The description of a deadline cannot be empty.");
        }
        if (!input.contains(" /by ")) {
            throw new FloresException("A deadline must have a /by time.");
        }
        return input.substring(9).split(" /by ");
    }

    /**
     * Extracts the description, start date, and end date for an Event task.
     * * @param input The raw input string (e.g., "event meeting /from 2026-01-28 /to 2026-01-29").
     *
     * @return A String array containing {description, from, to}.
     * @throws FloresException If the description is empty or /from or /to keywords are missing.
     */
    public static String[] getEventData(String input) throws FloresException {
        if (input.trim().equalsIgnoreCase("event")) {
            throw new FloresException("The description of an event cannot be empty.");
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new FloresException("An event must have /from and /to times.");
        }

        String content = input.substring(6);
        String[] eventParts = content.split(" /from ");
        String description = eventParts[0];
        String[] timeParts = eventParts[1].split(" /to ");

        return new String[]{description, timeParts[0], timeParts[1]};
    }

    /**
     * Extracts the task index from commands like mark, unmark, or delete.
     * The index is converted from 1-based (user input) to 0-based (internal list).
     * * @param input The raw input string.
     *
     * @param commandType The command word (e.g., "delete") used to calculate substring offset.
     * @return The 0-based index of the task.
     */
    public static int getIndex(String input, String commandType) {
        return Integer.parseInt(input.substring(commandType.length()).trim()) - 1;
    }
}