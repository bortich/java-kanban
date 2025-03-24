import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void tasksWithSameIdShouldBeEqual() {
        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("Test Task", "Description");
        Task task2 = new Task("Test Task", "Description");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны");
    }

    @Test
    void subtasksWithSameIdShouldBeEqual() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic("Epic", "Description");
        taskManager.addEpic(epic);

        Subtask subtask1 = new Subtask("Subtask", "Description", epic.getId());
        Subtask subtask2 = new Subtask("Subtask", "Description", epic.getId());

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым id должны быть равны");
    }

    @Test
    void epicsWithSameIdShouldBeEqual() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic1 = new Epic("Epic", "Description");
        Epic epic2 = new Epic("Epic", "Description");

        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        assertEquals(epic1, epic2, "Эпики с одинаковым id должны быть равны");
    }

    @Test
    void epicCannotAddItselfAsSubtask() {
        Epic epic = new Epic("Epic", "Description");

        assertThrows(IllegalArgumentException.class, () -> epic.addSubtask(epic.getId()),
                "Эпик не может быть добавлен в самого себя в качестве подзадачи");
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {

        Subtask subtask = new Subtask("Subtask", "Description", 2);

        assertNotEquals(subtask.getEpicId(), subtask.getId(), "Подзадача не может быть своим же эпиком");
    }
}


