public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        Task task1 = new Task(manager.createTaskId(), "Купить продукты", "Купить хлеб и молоко");
        manager.addTask(task1);
        manager.getTask(task1.getId());

        Task task2 = new Task(manager.createTaskId(), "Сделать домашку", "Решить задачи по математике");
        task2.setStatus(Status.IN_PROGRESS);
        manager.addTask(task2);
        manager.getTask(task2.getId());

        Task task3 = new Task(manager.createTaskId(), "Поехать в спортзал", "Провести тренировку");
        task3.setStatus(Status.DONE);
        manager.addTask(task3);
        manager.getTask(task3.getId());

        Epic epic1 = new Epic(manager.createEpicId(), "Подготовка к экзамену", "Подготовиться к экзаменам");
        manager.addEpic(epic1);
        manager.getEpic(epic1.getId());

        Epic epic2 = new Epic(manager.createEpicId(), "Путешествие", "Запланировать отпуск и поездку");
        manager.addEpic(epic2);
        manager.getEpic(epic2.getId());

        Subtask subtask1 = new Subtask(manager.createSubtaskId(), "Выучить главы", "Прочитать главы 1-3", epic1.getId());
        manager.addSubtask(subtask1);
        manager.getSubtask(subtask1.getId());

        Subtask subtask2 = new Subtask(manager.createSubtaskId(), "Провести практическую работу", "Подготовить презентацию", epic1.getId());
        subtask2.setStatus(Status.IN_PROGRESS);
        manager.addSubtask(subtask2);
        manager.getSubtask(subtask2.getId());

        Subtask subtask3 = new Subtask(manager.createSubtaskId(), "Забронировать билеты", "Выбрать и забронировать билеты в Турцию", epic2.getId());
        subtask3.setStatus(Status.NEW);
        manager.addSubtask(subtask3);
        manager.getSubtask(subtask3.getId());

        Subtask subtask4 = new Subtask(manager.createSubtaskId(), "Собрать чемодан", "Собрать все вещи для поездки", epic2.getId());
        subtask4.setStatus(Status.DONE);
        manager.addSubtask(subtask4);
        manager.getSubtask(subtask4.getId());

        System.out.println("История просмотров:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }

        System.out.println("\nВсе задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nВсе эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nВсе сабтаски:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("\nСабтаски для эпика 'Подготовка к экзамену':");
        for (Subtask subtask : manager.getSubtasksByEpicId(epic1.getId())) {
            System.out.println(subtask);
        }

        System.out.println("\nСабтаски для эпика 'Путешествие':");
        for (Subtask subtask : manager.getSubtasksByEpicId(epic2.getId())) {
            System.out.println(subtask);
        }
    }
}

