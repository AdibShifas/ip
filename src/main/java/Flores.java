import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
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
        loadTasks(items);
        while (true) {
            String input = sc.nextLine();

            try {
                System.out.println(horizontalLine);

                // Identify the command using Enums
                Command cmd = getCommand(input);

                switch (cmd) {
                    case LIST:
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < items.size(); i++) {
                            System.out.println((i + 1) + ". " + items.get(i));
                        }
                        System.out.println(horizontalLine);
                        continue;

                    case TODO:
                        if (input.trim().equalsIgnoreCase("todo")) {
                            throw new FloresException("The description of a todo cannot be empty.");
                        }
                        Task t = new Todo(input.substring(5));
                        items.add(t);
                        printAddedMessage(t, items.size());
                        saveTasks(items);
                        continue;

                    case DEADLINE:
                        if (input.trim().equalsIgnoreCase("deadline")) {
                            throw new FloresException("The description of a deadline cannot be empty.");
                        }
                        if (!input.contains(" /by ")) {
                            throw new FloresException("A deadline must have a /by time.");
                        }
                        try {
                            String[] parts = input.substring(9).split(" /by ");
                            Task d = new Deadline(parts[0], parts[1]);
                            items.add(d);
                            printAddedMessage(d, items.size());
                            saveTasks(items);
                        } catch (java.time.format.DateTimeParseException e) {
                            throw new FloresException("Please use the date format: yyyy-mm-dd (e.g., 2026-01-27)");
                        }
                        continue;

                    case EVENT:
                        if (input.trim().equalsIgnoreCase("event")) {
                            throw new FloresException("The description of an event cannot be empty.");
                        }
                        if (!input.contains(" /from ") || !input.contains(" /to ")) {
                            throw new FloresException("An event must have /from and /to times.");
                        }
                        try {
                            String[] eventParts = input.substring(6).split(" /from ");
                            String description = eventParts[0];
                            String[] timeParts = eventParts[1].split(" /to ");
                            Task e = new Event(description, timeParts[0], timeParts[1]);
                            items.add(e);
                            printAddedMessage(e, items.size());
                            saveTasks(items);
                        } catch (java.time.format.DateTimeParseException err) {
                            throw new FloresException("Please use the date format: yyyy-mm-dd (e.g., 2026-01-27)");
                        }
                        continue;

                    case MARK:
                        // used Google Gemini for this logic
                        int markIndex = Integer.parseInt(input.substring(5)) - 1;
                        items.get(markIndex).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(" " + items.get(markIndex));
                        System.out.println(horizontalLine);
                        saveTasks(items);
                        continue;

                    case UNMARK:
                        // used Google Gemini for this logic
                        int unmarkIndex = Integer.parseInt(input.substring(7)) - 1;
                        items.get(unmarkIndex).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(" " + items.get(unmarkIndex));
                        System.out.println(horizontalLine);
                        saveTasks(items);
                        continue;

                    case DELETE:
                        // deleting a task
                        int deleteIndex = Integer.parseInt(input.substring(7)) - 1;
                        if (deleteIndex < 0 || deleteIndex >= items.size()) {
                            throw new FloresException("That task number does not exist.");
                        }
                        Task removedTask = items.get(deleteIndex);
                        items.remove(deleteIndex);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("   " + removedTask);
                        System.out.println(" Now you have " + items.size() + " tasks in the list.");
                        System.out.println(horizontalLine);
                        saveTasks(items);
                        continue;

                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(horizontalLine);
                        break;

                    default:
                        // Handle unknown commands
                        throw new FloresException("I DONT KNOW WHAT THAT MEANS BRUH");
                }

                // Exit the while loop if the command was BYE
                if (cmd == Command.BYE) {
                    break;
                }

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

    // helper to map user input to a command enum
    private static Command getCommand(String input) {
        String cmd = input.split(" ")[0].toUpperCase();
        try {
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    public static void printAddedMessage(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void saveTasks(ArrayList<Task> items) {
        try {
            File folder = new File("./data");
            if (!folder.exists()) folder.mkdirs();
            FileWriter fw = new FileWriter("./data/flores.txt");
            for (Task t : items) {
                fw.write(t.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e){
            System.out.println("Error Saving: " + e.getMessage());
        }
    }

    public static void loadTasks(ArrayList<Task> items) {
        File file = new File("./data/flores.txt");
        if (!file.exists()) return;

        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(" \\| ");

                if (parts.length < 3) continue;

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                Task t = null;
                try {
                    if (type.equals("T")) {
                        t = new Todo(desc);
                    } else if (type.equals("D") && parts.length >= 4) {
                        t = new Deadline(desc, parts[3]);
                    } else if (type.equals("E") && parts.length >= 5) {
                        t = new Event(desc, parts[3], parts[4]);
                    }

                    if (t != null) {
                        if (isDone) t.markAsDone();
                        items.add(t);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("No previous save file found.");
        }
    }
}