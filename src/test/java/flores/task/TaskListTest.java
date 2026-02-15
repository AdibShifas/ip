package flores.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void add_validTask_success() {
        Task task = new Todo("read book");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void delete_validIndex_success() {
        Task task = new Todo("read book");
        taskList.add(task);
        Task deleted = taskList.delete(0);
        assertEquals(task, deleted);
        assertEquals(0, taskList.size());
    }

    @Test
    public void find_keyword_success() {
        taskList.add(new Todo("read book"));
        taskList.add(new Todo("return book"));
        taskList.add(new Todo("go for run"));

        TaskList found = taskList.find("book");
        assertEquals(2, found.size());
        assertEquals("read book", found.get(0).getDescription());
        assertEquals("return book", found.get(1).getDescription());
    }

    @Test
    public void hasDuplicate_duplicateTask_true() {
        Task task = new Todo("read book");
        taskList.add(task);
        assertTrue(taskList.hasDuplicate(new Todo("read book")));
    }

    @Test
    public void hasDuplicate_uniqueTask_false() {
        taskList.add(new Todo("read book"));
        assertFalse(taskList.hasDuplicate(new Todo("write code")));
    }
}
