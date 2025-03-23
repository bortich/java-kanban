import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task(1, "Test Task", "Description");
        Task task2 = new Task(1, "Test Task", "Description");

        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны");
    }

    @Test
    void subtasksWithSameIdShouldBeEqual() {
        Subtask subtask1 = new Subtask(2, "Subtask", "Description", 1);
        Subtask subtask2 = new Subtask(2, "Subtask", "Description", 1);

        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым id должны быть равны");
    }

    @Test
    void epicsWithSameIdShouldBeEqual() {
        Epic epic1 = new Epic(3, "Epic", "Description");
        Epic epic2 = new Epic(3, "Epic", "Description");

        assertEquals(epic1, epic2, "Эпики с одинаковым id должны быть равны");
    }

    @Test
    void epicCannotAddItselfAsSubtask() {
        Epic epic = new Epic(1, "Epic", "Description");

        assertThrows(IllegalArgumentException.class, () -> epic.addSubtask(epic.getId()),
                "Эпик не может быть добавлен в самого себя в качестве подзадачи");
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {
        Subtask subtask = new Subtask(2, "Subtask", "Description", 2);

        assertNotEquals(subtask.getEpicId(), subtask.getId(), "Подзадача не может быть своим же эпиком");
    }
}

