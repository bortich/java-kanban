import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class InMemoryTaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = Managers.getDefault();
    }

    @Test
    void addTaskAndHistory() {
        Task task = new Task(1, "Task 1", "Description 1");
        taskManager.addTask(task);

        taskManager.getTask(task.getId());

        List<Task> history = taskManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История должна содержать одну задачу.");
        assertEquals(task, history.get(0), "Задачи не совпадают.");
    }
}
