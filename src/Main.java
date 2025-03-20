public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task(manager.createTaskId(), "Купить продукты", "Купить хлеб, молоко, масло");
        Task task2 = new Task(manager.createTaskId(), "Заплатить за интернет", "Оплатить счет за интернет");
        Task task3 = new Task(manager.createTaskId(), "Сходить в спортзал", "Пробежка и растяжка");

        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);

        Epic epic1 = new Epic(manager.createEpicId(), "Подготовка к поездке", "Собрать вещи, забронировать отель");
        Epic epic2 = new Epic(manager.createEpicId(), "Учеба", "Подготовка к экзамену");
        Epic epic3 = new Epic(manager.createEpicId(), "Рабочий проект", "Разработка нового модуля");

        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.addEpic(epic3);

        Subtask subtask1 = new Subtask(manager.createSubtaskId(), "Собрать вещи", "Взять одежду и документы", epic1.getId());
        Subtask subtask2 = new Subtask(manager.createSubtaskId(), "Забронировать отель", "Найти и забронировать номер", epic1.getId());

        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        Subtask subtask3 = new Subtask(manager.createSubtaskId(), "Выучить главы 1-3", "Прочитать конспекты", epic2.getId());
        Subtask subtask4 = new Subtask(manager.createSubtaskId(), "Решить задачи", "Решить 20 примеров", epic2.getId());

        manager.addSubtask(subtask3);
        manager.addSubtask(subtask4);

        Subtask subtask5 = new Subtask(manager.createSubtaskId(), "Разработать код", "Создать рабочий код", epic3.getId());
        Subtask subtask6 = new Subtask(manager.createSubtaskId(), "Написать тесты", "Покрыть код тестами", epic3.getId());

        manager.addSubtask(subtask5);
        manager.addSubtask(subtask6);

        System.out.println("Список задач");
        System.out.println(manager.getAllTasks());

        System.out.println("\nСписок эпиков");
        System.out.println(manager.getAllEpics());

        System.out.println("\nСписок подзадач");
        System.out.println(manager.getAllSubtasks());

        System.out.println("\nПодзадачи эпика '" + epic1.getId() + "'");
        System.out.println(manager.getSubtasksByEpicId(epic1.getId()));

        System.out.println("\nПодзадачи эпика '" + epic2.getId() + "'");
        System.out.println(manager.getSubtasksByEpicId(epic2.getId()));

        System.out.println("\nПодзадачи эпика '" + epic3.getId() + "'");
        System.out.println(manager.getSubtasksByEpicId(epic3.getId()));

        task1.setStatus(Status.DONE);
        subtask1.setStatus(Status.IN_PROGRESS);
        subtask3.setStatus(Status.DONE);
        subtask4.setStatus(Status.IN_PROGRESS);
        subtask6.setStatus(Status.DONE);

        manager.updateTask(task1);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask3);
        manager.updateSubtask(subtask4);
        manager.updateSubtask(subtask6);

        System.out.println("\nПосле обновления статусов");
        System.out.println(manager.getAllEpics());

        manager.removeTask(task2.getId());
        manager.removeEpic(epic1.getId());

        System.out.println("\nПосле удаления одной задачи и одного эпика");
        System.out.println("Список задач: " + manager.getAllTasks());
        System.out.println("Список эпиков: " + manager.getAllEpics());
        System.out.println("Список подзадач: " + manager.getAllSubtasks());

        manager.removeAllSubtasks();
        System.out.println("\nПосле удаления всех подзадач");
        System.out.println("Список эпиков: " + manager.getAllEpics());

        manager.removeAllEpics();
        System.out.println("\nПосле удаления всех эпиков");
        System.out.println("Список эпиков: " + manager.getAllEpics());

        manager.removeAllTasks();
        System.out.println("\nПосле удаления всех задач");
        System.out.println("Список задач: " + manager.getAllTasks());
    }
}
