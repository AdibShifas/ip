public class Parser {
    public static Command getCommand(String input) {
        String cmd = input.split(" ")[0].toUpperCase();
        try {
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    // Extracts the description for a Todo task
    public static String getTodoDescription(String input) throws FloresException {
        if (input.trim().equalsIgnoreCase("todo")) {
            throw new FloresException("The description of a todo cannot be empty.");
        }
        return input.substring(5).trim();
    }

    // Returns an array where [0] is description and [1] is the deadline date
    public static String[] getDeadlineData(String input) throws FloresException {
        if (input.trim().equalsIgnoreCase("deadline")) {
            throw new FloresException("The description of a deadline cannot be empty.");
        }
        if (!input.contains(" /by ")) {
            throw new FloresException("A deadline must have a /by time.");
        }
        return input.substring(9).split(" /by ");
    }

    public static String[] getEventData(String input) throws FloresException {
        if (input.trim().equalsIgnoreCase("event")) {
            throw new FloresException("The description of an event cannot be empty.");
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new FloresException("An event must have /from and /to times.");
        }

        // Input: event project meeting /from 2026-01-28 /to 2026-01-29
        String content = input.substring(6); // remove "event "
        String[] eventParts = content.split(" /from ");
        String description = eventParts[0];
        String[] timeParts = eventParts[1].split(" /to ");

        // Returns {description, from, to}
        return new String[] { description, timeParts[0], timeParts[1] };
    }

    // Returns the index for Mark/Unmark/Delete commands
    public static int getIndex(String input, String commandType) {
        // commandType would be "mark", "unmark", or "delete"
        return Integer.parseInt(input.substring(commandType.length()).trim()) - 1;
    }
}