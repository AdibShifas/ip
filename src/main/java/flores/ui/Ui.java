package flores.ui;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import flores.task.Task;
import flores.task.TaskList;

/**
 * Deals with interactions with the user.
 * This class is responsible for displaying messages to the user and reading
 * user input.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner sc;

    /**
     * Initializes a new Ui object and sets up the scanner for user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(getWelcome());
    }

    public String getWelcome() {
        return LINE + "\nSup. I'm Flores.\nWhat do you want?\n" + LINE;
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The raw string command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a horizontal separator line to the console.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message with a custom prefix.
     *
     * @param message The error details to be displayed.
     */
    public void showError(String... message) {
        assert message != null : "Error message cannot be null";
        System.out.println(getError(message));
    }

    public String getError(String... message) {
        StringBuilder sb = new StringBuilder();
        for (String m : message) {
            sb.append(" Bruh... ").append(m).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Displays a message indicating that the save file could not be loaded.
     */
    public void showLoadingError() {
        System.out.println(" Couldn't load your tasks. Starting fresh I guess.");
    }

    /**
     * Prints a generic message to the user.
     *
     * @param message The text to be printed.
     */
    public void showMessage(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list after adding.
     */
    public void showAddedMessage(Task task, int size) {
        System.out.println(getAddedMessage(task, size));
    }

    public String getAddedMessage(Task task, int size) {
        return " Added. You have " + size + " tasks now.\n"
                + "   " + task;
    }

    /**
     * Displays a confirmation message when a task is successfully removed.
     *
     * @param task The task that was removed.
     * @param size The current number of tasks in the list after removal.
     */
    public void showRemovedMessage(Task task, int size) {
        System.out.println(getRemovedMessage(task, size));
    }

    public String getRemovedMessage(Task task, int size) {
        return " Deleted. You have " + size + " tasks left.\n"
                + "   " + task;
    }

    /**
     * Iterates through and displays the entire list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(getTaskList(tasks));
    }

    public String getTaskList(TaskList tasks) {
        return "Here. Try to finish these:\n"
                + IntStream.range(0, tasks.size())
                        .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                        .collect(Collectors.joining("\n"));
    }
}
