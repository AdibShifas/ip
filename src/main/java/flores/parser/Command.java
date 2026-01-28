package flores.parser;

/**
 * Represents the valid command types that the Flores application can process.
 */
public enum Command {
    /** Command to add a Todo task. */
    TODO,
    /** Command to add a Deadline task. */
    DEADLINE,
    /** Command to add an Event task. */
    EVENT,
    /** Command to mark a task as completed. */
    MARK,
    /** Command to mark a task as incomplete. */
    UNMARK,
    /** Command to remove a task from the list. */
    DELETE,
    /** Command to display all tasks. */
    LIST,
    /** Command to exit the application. */
    BYE,
    /** Represents an unrecognized or invalid command. */
    UNKNOWN
}