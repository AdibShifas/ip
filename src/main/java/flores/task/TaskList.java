package flores.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Filters the tasks to find descriptions containing the given keyword.
     *
     * @param keyword The string to search for within task descriptions.
     * @return A new TaskList containing only the matching tasks.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                filtered.add(t);
            }
        }
        return new TaskList(filtered);
    }
}