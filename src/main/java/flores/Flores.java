package flores;

import flores.exception.FloresException;
import flores.parser.Command;
import flores.parser.Parser;
import flores.storage.Storage;
import flores.task.*;
import flores.ui.Ui;

/**
 * The main class for the Flores chatbot application.
 * Flores is a task management tool that allows users to track todos, deadlines, and events.
 * It coordinates between the user interface, file storage, and command parsing logic.
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
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            try {
                Command cmd = Parser.getCommand(fullCommand);

                switch (cmd) {
                    case LIST:
                        ui.showTaskList(tasks);
                        break;

                    case TODO:
                        String todoDesc = Parser.getTodoDescription(fullCommand);
                        Task t = new Todo(todoDesc);
                        tasks.add(t);
                        ui.showAddedMessage(t, tasks.size());
                        break;

                    case DEADLINE:
                        String[] deadlineData = Parser.getDeadlineData(fullCommand);
                        Task d = new Deadline(deadlineData[0], deadlineData[1]);
                        tasks.add(d);
                        ui.showAddedMessage(d, tasks.size());
                        break;

                    case EVENT:
                        String[] eventData = Parser.getEventData(fullCommand);
                        Task e = new Event(eventData[0], eventData[1], eventData[2]);
                        tasks.add(e);
                        ui.showAddedMessage(e, tasks.size());
                        break;

                    case MARK:
                        int markIdx = Parser.getIndex(fullCommand, "mark");
                        tasks.get(markIdx).markAsDone();
                        ui.showMessage("Nice! I've marked this task as done:\n " + tasks.get(markIdx));
                        break;

                    case UNMARK:
                        int unmarkIdx = Parser.getIndex(fullCommand, "unmark");
                        tasks.get(unmarkIdx).markAsNotDone();
                        ui.showMessage("OK, I've marked this task as not done yet:\n " + tasks.get(unmarkIdx));
                        break;

                    case DELETE:
                        int delIdx = Parser.getIndex(fullCommand, "delete");
                        if (delIdx < 0 || delIdx >= tasks.size()) {
                            throw new FloresException("That task number does not exist.");
                        }
                        Task removed = tasks.delete(delIdx);
                        ui.showRemovedMessage(removed, tasks.size());
                        break;

                    case FIND:
                        String keyword = Parser.getFindKeyword(fullCommand);
                        TaskList foundTasks = tasks.find(keyword);
                        ui.showTaskList(foundTasks);
                        break;

                    case BYE:
                        isExit = true;
                        ui.showMessage("Bye. Hope to see you again soon!");
                        break;

                    default:
                        throw new FloresException("I DONT KNOW WHAT THAT MEANS BRUH");
                }

                if (cmd != Command.BYE && cmd != Command.UNKNOWN) {
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
}