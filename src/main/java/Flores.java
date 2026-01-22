import java.util.Scanner;
import java.util.ArrayList;

public class Flores {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        // greet user
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Flores\nWhat can I do for you?");
        System.out.println(horizontalLine);
        Scanner sc = new Scanner(System.in);
        // arraylist to store each input
        ArrayList<Task> items = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();

            try {
                System.out.println(horizontalLine);
                if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println((i + 1) + ". " + items.get(i));
                    }
                    System.out.println(horizontalLine);
                    continue;
                }

                if (input.startsWith("todo")) {
                    if (input.trim().equalsIgnoreCase("todo")) {
                        throw new FloresException("The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(input.substring(5));
                    items.add(t);
                    printAddedMessage(t, items.size());
                    continue;

                } else if (input.startsWith("deadline")) {
                    if (input.trim().equalsIgnoreCase("deadline")) {
                        throw new FloresException("The description of a deadline cannot be empty.");
                    }
                    if (!input.contains(" /by ")) {
                        throw new FloresException("A deadline must have a /by time.");
                    }
                    // Splits "deadline return book /by Sunday" into ["return book", "Sunday"]
                    String[] parts = input.substring(9).split(" /by ");
                    Task t = new Deadline(parts[0], parts[1]);
                    items.add(t);
                    printAddedMessage(t, items.size());
                    continue;

                } else if (input.startsWith("event")) {
                    if (input.trim().equalsIgnoreCase("event")) {
                        throw new FloresException("The description of an event cannot be empty.");
                    }
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new FloresException("An event must have /from and /to times.");
                    }
                    // Parses "event meeting /from Mon 2pm /to 4pm"
                    String[] parts = input.substring(6).split(" /from ");
                    String description = parts[0];
                    String[] timeParts = parts[1].split(" /to ");
                    Task t = new Event(description, timeParts[0], timeParts[1]);
                    items.add(t);
                    printAddedMessage(t, items.size());
                    continue;
                }

                if (input.startsWith("mark ")) {
                    // used Google Gemini for this logic
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    items.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + items.get(index));
                    System.out.println(horizontalLine);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    // used Google Gemini for this logic
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    items.get(index).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + items.get(index));
                    System.out.println(horizontalLine);
                    continue;
                }

                // deleting a task
                if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= items.size()) {
                        throw new FloresException("That task number does not exist.");
                    }
                    Task removedTask = items.get(index);
                    items.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + items.size() + " tasks in the list.");
                    System.out.println(horizontalLine);
                    continue;
                }

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(horizontalLine);
                    break;
                }

                // Handle unknown commands
                throw new FloresException("I DONT KNOW WHAT THAT MEANS BRUH");

            } catch (FloresException e) {
                System.out.println(" HUH?? " + e.getMessage());
                System.out.println(horizontalLine);
            } catch (Exception e) {
                System.out.println(" OOPS!!! Something went wrong: " + e.getMessage());
                System.out.println(horizontalLine);
            }
        }
        sc.close();
    }

    public static void printAddedMessage(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}