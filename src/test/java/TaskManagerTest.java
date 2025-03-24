import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class TaskManagerTest {
    private TaskManager taskManager;
    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    void taskEqualityById() {
        Task task1 = new Task("Test Task", "Description");
        Task task2 = new Task("Test Task", "Description");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны.");
    }

    @Test
    void subtaskAndEpicEqualityById() {
        Epic epic = new Epic("Epic", "Description");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Subtask", "Description", epic.getId());
        taskManager.addSubtask(subtask);

        assertEquals(epic, subtask, "Объекты с одинаковым ID должны быть равны.");
    }

    @Test
    void epicCannotBeSubtaskOfItself() {
        Epic epic = new Epic("Epic", "Description");

        assertThrows(IllegalArgumentException.class, () -> epic.addSubtask(epic.getId()),
                "Эпик не может быть добавлен в самого себя в качестве подзадачи");
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {
        Subtask subtask = new Subtask("Subtask", "Description", 2);

        assertNotEquals(subtask.getEpicId(), subtask.getId(), "Подзадача не может быть своим же эпиком.");
    }

    @Test
    void managersAreProperlyInitialized() {
        assertNotNull(taskManager, "Менеджер задач должен быть проинициализирован.");
        assertNotNull(historyManager, "Менеджер истории должен быть проинициализирован.");
    }

    @Test
    void inMemoryTaskManagerStoresAndRetrievesTasks() {
        Task task = new Task("Test Task", "Description");
        taskManager.addTask(task);

        assertEquals(task, taskManager.getTask(task.getId()), "Менеджер должен находить задачу по ID.");
    }

    @Test
    void tasksWithGeneratedAndSetIdsDoNotConflict() {
        Task task1 = new Task("Task 1", "Description");
        Task task2 = new Task("Task 2", "Description");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        assertNotEquals(task1.getId(), task2.getId(), "ID задач должны быть уникальными.");
    }

    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.addTask(task);

        final Task savedTask = taskManager.getTask(task.getId());
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();
        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void addTaskToHistory() {
        Task task = new Task("Test Task", "Description");
        historyManager.add(task);

        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История должна содержать одну задачу.");
    }
}

