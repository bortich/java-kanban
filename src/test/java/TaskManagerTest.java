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
        Task task1 = new Task(1, "Test Task", "Description");
        Task task2 = new Task(1, "Test Task", "Description");
        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны.");
    }

    @Test
    void subtaskAndEpicEqualityById() {
        Epic epic = new Epic(1, "Epic", "Description");
        Subtask subtask = new Subtask(1, "Subtask", "Description", epic.getId());
        assertEquals(epic, subtask, "Объекты с одинаковым ID должны быть равны.");
    }

    @Test
    void epicCannotBeSubtaskOfItself() {
        Epic epic = new Epic(1, "Epic", "Description");
        epic.addSubtask(epic.getId());
        assertFalse(epic.getSubtaskIds().contains(epic.getId()), "Эпик не должен содержать себя как подзадачу.");
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {
        Subtask subtask = new Subtask(1, "Subtask", "Description", 1);
        assertNotEquals(subtask.getId(), subtask.getEpicId(), "Подзадача не должна быть своим же эпиком.");
    }

    @Test
    void managersAreProperlyInitialized() {
        assertNotNull(taskManager, "Менеджер задач должен быть проинициализирован.");
        assertNotNull(historyManager, "Менеджер истории должен быть проинициализирован.");
    }

    @Test
    void inMemoryTaskManagerStoresAndRetrievesTasks() {
        Task task = new Task(1, "Test Task", "Description");
        taskManager.addTask(task);
        assertEquals(task, taskManager.getTask(1), "Менеджер должен находить задачу по ID.");
    }

    @Test
    void tasksWithGeneratedAndSetIdsDoNotConflict() {
        Task task1 = new Task(taskManager.createTaskId(), "Task 1", "Description");
        Task task2 = new Task(5, "Task 2", "Description");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        assertNotEquals(task1.getId(), task2.getId(), "ID задач должны быть уникальными.");
    }

    @Test
    void addNewTask() {
        Task task = new Task(1, "Test addNewTask", "Test addNewTask description");
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
        Task task = new Task(1, "Test Task", "Description");
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История должна содержать одну задачу.");
    }
}
