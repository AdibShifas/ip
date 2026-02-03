package flores.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be completed by a specific date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The details of the task.
     * @param by          The deadline date in yyyy-mm-dd format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}