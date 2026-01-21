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
        ArrayList<String> items = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            System.out.println(horizontalLine);
            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
                System.out.println(horizontalLine);
                continue;
            }
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }
            items.add(input);
            System.out.println("added: " + input);
            System.out.println(horizontalLine);

        }
        sc.close();
    }
}
