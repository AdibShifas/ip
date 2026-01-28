package flores.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import flores.task.Task;
import flores.task.Todo;
import flores.task.Deadline;
import flores.task.Event;

/**
 * Handles the loading and saving of task data to a local file.
 * This class is responsible for the persistence of the task list in the Flores application.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The path to the file where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the provided list of tasks to the storage file.
     * If the directory or file does not exist, they will be created.
     *
     * @param items The list of tasks to be written to the file.
     */
    public void save(ArrayList<Task> items) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);
            for (Task t : items) {
                fw.write(t.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error Saving: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the storage file.
     * Parses each line of the file and reconstructs the appropriate Task objects.
     * Returns an empty list if the file does not exist.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> items = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return items;

        try (Scanner s = new Scanner(file)) {
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
        } catch (FileNotFoundException e) {
            System.out.println("No save file found.");
        }
        return items;
    }
}