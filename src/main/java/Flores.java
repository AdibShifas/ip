import java.util.Scanner;
public class Flores {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        //greet user
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Flores\nWhat can I do for you?");
        System.out.println(horizontalLine);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println(horizontalLine);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }
            System.out.println(input);
            System.out.println(horizontalLine);

        }
        sc.close();
    }
}
