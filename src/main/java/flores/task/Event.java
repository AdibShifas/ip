package flores.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task that occurs within a specific time range.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with a description and a time duration.
     *
     * @param description The details of the event.
     * @param from The start date in yyyy-mm-dd format.
     * @param to The end date in yyyy-mm-dd format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(fmt)
                + " to: " + to.format(fmt) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}