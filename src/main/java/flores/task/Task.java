package flores.task;
/**
 * Represents a generic task in the Flores application.
 * A task consists of a description and a status indicating whether it is completed.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * Initially, the task is marked as not done.
     *
     * @param description The details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns true if task is completed, false otherwise.
     *
     * @return Whether task is completed or not.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task for UI display.
     *
     * @return A formatted string showing status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     *
     * @return A formatted string suitable for saving to a text file.
     */
    public String toFileString() {
        String status = isDone ? "1" : "0";
        return status + " | " + getDescription();
    }
}
