package flores.ui;

import java.util.Scanner;
import flores.task.Task;
import flores.task.TaskList;

/**
 * Deals with interactions with the user.
 * This class is responsible for displaying messages to the user and reading user input.
 */
public class Ui {
    private Scanner sc;
    private final String LINE = "____________________________________________________________";

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
        System.out.println(LINE);
        System.out.println("Hello! I'm Flores\nWhat can I do for you?");
        System.out.println(LINE);
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
    public void showError(String message) {
        System.out.println(" HUH?? " + message);
    }

    /**
     * Displays a message indicating that the save file could not be loaded.
     */
    public void showLoadingError() {
        System.out.println(" OOPS!!! I couldn't load your tasks. Starting with a fresh list!");
    }

    /**
     * Prints a generic message to the user.
     *
     * @param message The text to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list after adding.
     */
    public void showAddedMessage(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a confirmation message when a task is successfully removed.
     *
     * @param task The task that was removed.
     * @param size The current number of tasks in the list after removal.
     */
    public void showRemovedMessage(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * Iterates through and displays the entire list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}