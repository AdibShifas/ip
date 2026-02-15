package flores.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a specific time range.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event task with a description and a time duration.
     *
     * @param description The details of the event.
     * @param from        The start date in yyyy-mm-dd format.
     * @param to          The end date in yyyy-mm-dd format.
     * @throws FloresException If the start date is after the end date.
     */
    public Event(String description, String from, String to) throws flores.exception.FloresException {
        super(description);
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());

        if (this.from.isAfter(this.to)) {
            // Gemini: Added logic check to ensure start date is before end date.
            throw new flores.exception.FloresException("End date can't be before start date. Time travel isn't real.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(fmt)
                + " to: " + to.format(fmt) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return from.equals(event.from) && to.equals(event.to);
    }

    @Override
    public int hashCode() {
        return helperHashCode(super.hashCode(), from, to);
    }
}
