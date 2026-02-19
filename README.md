# Flores

> **View the full User Guide [here](https://adibshifas.github.io/ip/).**

Flores is a nonchalant task management chatbot that helps you keep track of your todos, deadlines, and events. It doesn't care much, but it gets the job done.

## Features

-   **Task Tracking**: Add `todo`, `deadline`, and `event` tasks.
-   **Storage**: Automatically saves your tasks to `data/flores.txt`.
-   **Nonchalant Personality**: Flores has an attitude. Don't take it personally.
-   **Robust Error Handling**:
    -   Strict date validation (`yyyy-mm-dd`).
    -   Logical checks (e.g., event start date must be before end date).
    -   Duplicate task detection.
    -   Graceful handling of corrupted save files.
-   **GUI**: A clean JavaFX interface with circular profile pictures and styled dialog boxes.

## Usage

### Adding Tasks

-   **Todo**: `todo <description>`
    -   Example: `todo read book`
-   **Deadline**: `deadline <description> /by <date>`
    -   Example: `deadline return book /by 2026-02-15`
-   **Event**: `event <description> /from <start> /to <end>`
    -   Example: `event project meeting /from 2026-02-16 /to 2026-02-17`

### Managing Tasks

-   **List**: `list` - Shows all tasks.
-   **Mark**: `mark <index>` - Marks a task as done.
-   **Unmark**: `unmark <index>` - Marks a task as not done.
-   **Delete**: `delete <index>` - Removes a task.
-   **Find**: `find <keyword>` - Searches for tasks.

### Exiting

-   **Bye**: `bye` - Closes the application (after a short delay).

## Setup

1.  Ensure you have **Java 17** or later installed.
2.  Clone the repository.
3.  Run the application using Gradle:
    ```bash
    ./gradlew run
    ```
4.  Run tests:
    ```bash
    ./gradlew test
    ```
