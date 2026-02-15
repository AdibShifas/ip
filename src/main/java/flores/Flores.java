package flores;

import flores.exception.FloresException;
import flores.parser.Command;
import flores.parser.Parser;
import flores.storage.Storage;
import flores.task.Deadline;
import flores.task.Event;
import flores.task.Task;
import flores.task.TaskList;
import flores.task.Todo;
import flores.ui.Ui;

/**
 * The main class for the Flores chatbot application.
 * Flores is a task management tool that allows users to track todos, deadlines,
 * and events.
 * It coordinates between the user interface, file storage, and command parsing
 * logic.
 */
public class Flores {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a new Flores application instance.
     * Sets up the UI, loads existing tasks from the specified file path,
     * and prepares the task list.
     *
     * @param filePath The local system path where task data is saved and loaded.
     */
    public Flores(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the main execution loop of the Flores application.
     * Displays the welcome message and continuously processes user commands
     * until the "bye" command is received.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public void run() {
        assert ui != null : "Ui should be initialized";
        assert storage != null : "Storage should be initialized";
        assert tasks != null : "TaskList should be initialized";
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            try {
                Command cmd = Parser.getCommand(fullCommand);

                if (cmd == Command.BYE) {
                    isExit = true;
                }
                String response = executeCommand(cmd, fullCommand);
                ui.showMessage(response);

                if (cmd != Command.BYE && cmd != Command.UNKNOWN && cmd != Command.LIST && cmd != Command.FIND) {
                    storage.save(tasks.getAll());
                }

            } catch (FloresException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage());
            }
            ui.showLine();
        }
    }

    /**
     * The entry point for starting the Flores application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Flores("./data/flores.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public String getResponse(String input) {
        try {
            assert input != null : "Input cannot be null";
            Command cmd = Parser.getCommand(input);
            String response = executeCommand(cmd, input);

            if (cmd != Command.BYE && cmd != Command.UNKNOWN && cmd != Command.LIST && cmd != Command.FIND) {
                storage.save(tasks.getAll());
            }
            return response;
        } catch (FloresException e) {
            return ui.getError(e.getMessage());
        } catch (Exception e) {
            return ui.getError("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Retrieves the welcome message mostly for the GUI.
     *
     * @return The welcome message string.
     */
    public String getWelcome() {
        return ui.getWelcome();
    }

    /**
     * Executes the specific command based on the parsed command type.
     *
     * @param cmd   The command type.
     * @param input The full user input.
     * @return The response string to be displayed to the user.
     * @throws FloresException If an error occurs during execution.
     */
    private String executeCommand(Command cmd, String input) throws FloresException {
        // Gemini: Structured command delegation for improved readability and
        // maintenance.
        switch (cmd) {
        case LIST:
            assert tasks != null : "TaskList should be initialized before listing";
            return ui.getTaskList(tasks);
        case TODO:
            return executeTodo(input);
        case DEADLINE:
            return executeDeadline(input);
        case EVENT:
            return executeEvent(input);
        case MARK:
            return executeMark(input);
        case UNMARK:
            return executeUnmark(input);
        case DELETE:
            return executeDelete(input);
        case FIND:
            return executeFind(input);
        case BYE:
            return "Bye. Whatever.";
        default:
            throw new FloresException("I don't get it. Keep it simple.");
        }
    }

    /**
     * Handles the execution of the 'todo' command.
     * Adds a new Todo task to the task list.
     *
     * @param input The full user input string.
     * @return The response message verifying the addition.
     * @throws FloresException If the todo description is invalid or a duplicate
     *                         exists.
     */
    // Gemini: Enhanced method with documentation and input validation.
    private String executeTodo(String input) throws FloresException {
        String todoDesc = Parser.getTodoDescription(input);
        Task t = new Todo(todoDesc);
        if (tasks.hasDuplicate(t)) {
            throw new FloresException("You already have this. One is enough.");
        }
        tasks.add(t);
        return ui.getAddedMessage(t, tasks.size());
    }

    /**
     * Handles the execution of the 'deadline' command.
     * Adds a new Deadline task to the task list.
     *
     * @param input The full user input string.
     * @return The response message verifying the addition.
     * @throws FloresException If the deadline format is invalid or a duplicate
     *                         exists.
     */
    // Gemini: Added deadline validation and documentation.
    private String executeDeadline(String input) throws FloresException {
        String[] deadlineData = Parser.getDeadlineData(input);
        Task d = new Deadline(deadlineData[0], deadlineData[1]);
        if (tasks.hasDuplicate(d)) {
            throw new FloresException("You already have this. One is enough.");
        }
        tasks.add(d);
        return ui.getAddedMessage(d, tasks.size());
    }

    /**
     * Handles the execution of the 'event' command.
     * Adds a new Event task to the task list.
     *
     * @param input The full user input string.
     * @return The response message verifying the addition.
     * @throws FloresException If the event format is invalid or a duplicate exists.
     */
    // Gemini: Implemented event handling logic with docs.
    private String executeEvent(String input) throws FloresException {
        String[] eventData = Parser.getEventData(input);
        Task e = new Event(eventData[0], eventData[1], eventData[2]);
        if (tasks.hasDuplicate(e)) {
            throw new FloresException("You already have this. One is enough.");
        }
        tasks.add(e);
        return ui.getAddedMessage(e, tasks.size());
    }

    /**
     * Handles the execution of the 'mark' command.
     * Marks a task as done.
     *
     * @param input The full user input string.
     * @return The response message confirming the task is marked as done.
     */
    private String executeMark(String input) {
        int markIdx = Parser.getIndex(input, "mark");
        tasks.get(markIdx).markAsDone();
        return "Done. Finally.\n " + tasks.get(markIdx);
    }

    /**
     * Handles the execution of the 'unmark' command.
     * Marks a task as not done.
     *
     * @param input The full user input string.
     * @return The response message confirming the task is marked as not done.
     */
    private String executeUnmark(String input) {
        int unmarkIdx = Parser.getIndex(input, "unmark");
        tasks.get(unmarkIdx).markAsNotDone();
        return "Not done? Make up your mind.\n " + tasks.get(unmarkIdx);
    }

    /**
     * Handles the execution of the 'delete' command.
     * Removes a task from the task list.
     *
     * @param input The full user input string.
     * @return The response message confirming the removal.
     * @throws FloresException If the index is invalid.
     */
    // Gemini: Added delete functionality with index checks.
    private String executeDelete(String input) throws FloresException {
        int delIdx = Parser.getIndex(input, "delete");
        if (delIdx < 0 || delIdx >= tasks.size()) {
            throw new FloresException("Invalid number. Can't you count?");
        }
        Task removed = tasks.delete(delIdx);
        return ui.getRemovedMessage(removed, tasks.size());
    }

    /**
     * Handles the execution of the 'find' command.
     * Searches for tasks containing the keyword.
     *
     * @param input The full user input string.
     * @return The list of found tasks.
     * @throws FloresException If the keyword is missing.
     */
    // Gemini: Added find command execution logic.
    private String executeFind(String input) throws FloresException {
        String keyword = Parser.getFindKeyword(input);
        TaskList foundTasks = tasks.find(keyword);
        return ui.getTaskList(foundTasks);
    }
}
