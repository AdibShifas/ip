package flores.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flores.task.Task;
import flores.task.Todo;

public class StorageTest {
    private static final String TEST_FILE_PATH = "data/test_storage.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void saveAndLoad_validTasks_success() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("write code"));

        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(2, loadedTasks.size());
        assertEquals("[T][ ] read book", loadedTasks.get(0).toString());
        assertEquals("[T][ ] write code", loadedTasks.get(1).toString());
    }

    @Test
    public void load_nonExistentFile_emptyList() {
        ArrayList<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
    }
}
