import java.util.List;

public interface TaskManager {
    Integer createTaskId();
    Integer createEpicId();
    Integer createSubtaskId();

    void addTask(Task task);
    void addEpic(Epic epic);
    void addSubtask(Subtask subtask);

    void updateTask(Task task);
    void updateSubtask(Subtask subtask);
    void updateEpic(Epic epic);

    void removeTask(int id);
    void removeSubtask(int id);
    void removeEpic(int id);

    Task getTask(int id);
    Epic getEpic(int id);
    Subtask getSubtask(int id);

    List<Task> getAllTasks();
    List<Epic> getAllEpics();
    List<Subtask> getAllSubtasks();
    List<Subtask> getSubtasksByEpicId(int epicId);

    List<Task> getHistory();
}
