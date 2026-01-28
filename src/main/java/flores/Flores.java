package flores;

import flores.task.Task;
import flores.task.Todo;
import flores.task.Deadline;
import flores.task.Event;
import flores.task.TaskList; // If you moved TaskList to flores.task
import flores.ui.Ui;
import flores.storage.Storage;
import flores.parser.Parser;
import flores.parser.Command;
import flores.exception.FloresException;

public class Flores {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Flores(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            // Instead of input
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

                    case BYE:
                        isExit = true;
                        ui.showMessage("Bye. Hope to see you again soon!");
                        break;

                    default:
                        throw new FloresException("I DONT KNOW WHAT THAT MEANS BRUH");
                }

                // Only saves if the command wasn't BYE or UNKNOWN
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

    public static void main(String[] args) {
        new Flores("./data/flores.txt").run();
    }
}
