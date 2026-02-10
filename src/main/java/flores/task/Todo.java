package flores.task;

/**
 * Represents a simple task without any date or time constraints.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The details of the todo item.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
