package flores.ui;
import java.util.Scanner;

import flores.task.Task;
import flores.task.TaskList;

public class Ui {
    private Scanner sc;
    private final String LINE = "____________________________________________________________";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Flores\nWhat can I do for you?");
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(" HUH?? " + message);
    }

    public void showLoadingError() {
        System.out.println(" OOPS!!! I couldn't load your tasks. Starting with a fresh list!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showAddedMessage(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showRemovedMessage(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}