public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task(manager.generateId(), "Купить продукты", "Купить хлеб, молоко, масло");
        Task task2 = new Task(manager.generateId(), "Сходить в спортзал", "Тренировка на 1 час");

        manager.addTask(task1);
        manager.addTask(task2);

        Epic epic1 = new Epic(manager.generateId(), "Подготовка к поездке", "Собрать вещи, забронировать отель");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask(manager.generateId(), "Собрать вещи", "Взять одежду и документы", epic1.getId());
        Subtask subtask2 = new Subtask(manager.generateId(), "Забронировать отель", "Найти и забронировать номер", epic1.getId());

        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        Epic epic2 = new Epic(manager.generateId(), "Подготовка к экзамену", "Выучить материал, повторить");
        manager.addEpic(epic2);

        Subtask subtask3 = new Subtask(manager.generateId(), "Выучить материал", "Прочитать учебник", epic2.getId());
        manager.addSubtask(subtask3);

        System.out.println("Список задач:");
        System.out.println(manager.getAllTasks());

        System.out.println("Список эпиков:");
        System.out.println(manager.getAllEpics());

        System.out.println("Список подзадач:");
        System.out.println(manager.getAllSubtasks());

        task1.setStatus(Status.DONE);
        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        subtask3.setStatus(Status.IN_PROGRESS);

        manager.updateTask(task1);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);
        manager.updateSubtask(subtask3);

        System.out.println("\nПосле изменения статусов:");
        System.out.println(manager.getAllEpics());

        manager.removeTask(task2.getId());
        manager.removeEpic(epic1.getId());

        System.out.println("\nПосле удаления задачи и эпика:");
        System.out.println("Список задач:");
        System.out.println(manager.getAllTasks());

        System.out.println("Список эпиков:");
        System.out.println(manager.getAllEpics());

        System.out.println("Список подзадач:");
        System.out.println(manager.getAllSubtasks());
    }
}
