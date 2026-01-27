import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        // Assumes input format is yyyy-mm-dd
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
