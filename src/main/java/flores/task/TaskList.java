package flores.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Contains operations to add, delete, get, and filter tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        assert t != null : "Task to add cannot be null";
        tasks.add(t);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to delete.
     * @return The deleted task.
     */
    public Task delete(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for delete";
        return tasks.remove(index);
    }

    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for get";
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
        assert keyword != null : "Keyword cannot be null";
        ArrayList<Task> filtered = tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        return new TaskList(filtered);
    }

    /**
     * Checks if the list contains a task identical to the given task.
     *
     * @param t The task to check for duplicates.
     * @return True if a duplicate exists, false otherwise.
     */
    public boolean hasDuplicate(Task t) {
        return tasks.contains(t);
    }
}
