import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class InMemoryHistoryManagerTest {
    private HistoryManager historyManager;
    private Task task;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        task = new Task(1, "History Task", "Tracking");
    }

    @Test
    void shouldAddTasksToHistory() {
        historyManager.add(task);
        List<Task> history = historyManager.getHistory();

        assertNotNull(history, "История просмотров не должна быть null");
        assertEquals(1, history.size(), "История просмотров должна содержать одну задачу");
        assertEquals(task, history.get(0), "Добавленная задача не соответствует сохранённой");
    }
}

