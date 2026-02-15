# User Guide

Flores is a **nonchalant task management chatbot** designed to help you organize your life without the usual enthusiastic clutter. It tracks your todos, deadlines, and events with a distinct lack of enthusiasm, but it gets the job done.

## Quick Start

1.  Ensure you have Java `17` or above installed in your Computer.
2.  Download the latest release from [here](https://github.com/AdibShifas/ip/releases).
3.  Copy the file to the folder you want to use as the _home folder_ for your Flores.
4.  Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar flores.jar` command to run the application.
5.  Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
6.  Some example commands you can try:

    *   `list` : Lists all tasks.
    *   `todo read book` : Adds a todo task with the description "read book".
    *   `deadline return book /by 2026-02-15` : Adds a deadline task.
    *   `delete 1` : Deletes the 1st task in the current list.
    *   `bye` : Exits the app.

7.  Refer to the [Features](#features) below for details of each command.

## Features

### Nonchalant Personality
Flores isn't your typical overly helpful assistant. Expect responses like "Bruh..." or "Whatever." when things go wrong, and a simple "Added." when things go right.

### Robust Error Handling
Flores is smart enough to catch your mistakes:
*   **Date Validation**: Dates must be in `yyyy-mm-dd` format.
*   **Logic Checks**: You can't end an event before it starts ("Time travel isn't real.").
*   **Duplicate Detection**: Prevents you from adding the exact same task twice.
*   **Data Protection**: If your save file gets corrupted, Flores gracefully skips the bad lines instead of crashing.

## Command Reference

### `todo` - Add a Todo
Adds a task without any date/time attached to it.

*   **Format:** `todo <description>`
*   **Example:** `todo read book`
*   **Expected Output:**
    ```
     Added. You have 5 tasks now.
       [T][ ] read book
    ```

### `deadline` - Add a Deadline
Adds a task that needs to be done before a specific date.

*   **Format:** `deadline <description> /by <date>`
*   **Date Format:** `yyyy-mm-dd`
*   **Example:** `deadline return book /by 2026-02-15`
*   **Expected Output:**
    ```
     Added. You have 6 tasks now.
       [D][ ] return book (by: Feb 15 2026)
    ```

### `event` - Add an Event
Adds a task that starts at a specific time and ends at a specific time.

*   **Format:** `event <description> /from <start-date> /to <end-date>`
*   **Date Format:** `yyyy-mm-dd`
*   **Constraint:** `start-date` must be before `end-date`.
*   **Example:** `event project meeting /from 2026-02-16 /to 2026-02-17`
*   **Expected Output:**
    ```
     Added. You have 7 tasks now.
       [E][ ] project meeting (from: Feb 16 2026 to: Feb 17 2026)
    ```

### `list` - List all tasks
Shows a list of all tasks in the task list.

*   **Format:** `list`

### `mark` - Mark a task as done
Marks a task as completed.

*   **Format:** `mark <index>`
*   **Example:** `mark 1` marks the first task in the list as done.

### `unmark` - Mark a task as not done
Marks a task as incomplete.

*   **Format:** `unmark <index>`
*   **Example:** `unmark 1` marks the first task in the list as not done.

### `delete` - Delete a task
Removes a task from the task list.

*   **Format:** `delete <index>`
*   **Example:** `delete 1` removes the first task.

### `find` - Find tasks
Finds tasks whose description contains the given keyword.

*   **Format:** `find <keyword>`
*   **Example:** `find book` returns tasks like "read book" and "return book".

### `bye` - Exit
Exits the application.

*   **Format:** `bye`

## FAQ

**Q: How do I save my data?**
A: Flores automatically saves your data to `data/flores.txt` after every command that modifies the task list.

**Q: Where is my data file?**
A: Check the `data` folder in the same directory as the application executable.

**Q: What happens if I enter an invalid date?**
A: Flores will complain: `Bruh... Dates must be yyyy-mm-dd. Basic stuff.`

## Command Summary

| Action | Format | Example |
| :--- | :--- | :--- |
| **Add Todo** | `todo <description>` | `todo read book` |
| **Add Deadline** | `deadline <description> /by <date>` | `deadline return book /by 2026-02-15` |
| **Add Event** | `event <description> /from <start> /to <end>` | `event meeting /from 2026-02-16 /to 2026-02-17` |
| **List Tasks** | `list` | `list` |
| **Mark Done** | `mark <index>` | `mark 1` |
| **Mark Undone** | `unmark <index>` | `unmark 1` |
| **Delete Task** | `delete <index>` | `delete 1` |
| **Find Task** | `find <keyword>` | `find book` |
| **Exit** | `bye` | `bye` |
