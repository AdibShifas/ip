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
            System.out.println(horizontalLine);
            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
                System.out.println(horizontalLine);
                continue;
            }
            if (input.startsWith("mark ")) {
                // used google gemini for this logic
                int index = Integer.parseInt(input.substring(5)) - 1;
                items.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + items.get(index));
                System.out.println(horizontalLine);
                continue;
            }

            if (input.startsWith("unmark ")) {
                // used google gemini for this logic
                int index = Integer.parseInt(input.substring(7)) - 1;
                items.get(index).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + items.get(index));
                System.out.println(horizontalLine);
                continue;
            }

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }

            items.add(new Task(input));
            System.out.println("added: " + input);
            System.out.println(horizontalLine);

        }
        sc.close();
    }
}
