public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        Task task1 = new Task("Купить продукты", "Сходить в магазин и купить продукты");
        Task task2 = new Task("Сделать домашку", "Написать домашку по математике");
        Task task3 = new Task("Поехать в спортзал", "Пойти на тренировку в спортзал");

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        Epic epic1 = new Epic("Подготовка к экзамену", "Подготовиться к экзамену по программированию");
        Epic epic2 = new Epic("Путешествие", "Планирование и подготовка к путешествию");

        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        Subtask subtask1 = new Subtask("Учить теорию", "Прочитать теорию по программированию", epic1.getId());
        Subtask subtask2 = new Subtask("Решать задачи", "Решить задачи по программированию", epic1.getId());
        Subtask subtask3 = new Subtask("Паковать вещи", "Паковать вещи для путешествия", epic2.getId());

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);

        task1.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task1);

        task2.setStatus(Status.DONE);
        taskManager.updateTask(task2);

        task3.setStatus(Status.NEW);
        taskManager.updateTask(task3);

        subtask1.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);

        subtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask2);

        subtask3.setStatus(Status.NEW);
        taskManager.updateSubtask(subtask3);

        taskManager.getTask(task1.getId());
        taskManager.getTask(task2.getId());
        taskManager.getTask(task3.getId());

        taskManager.getEpic(epic1.getId());
        taskManager.getEpic(epic2.getId());

        taskManager.getSubtask(subtask1.getId());
        taskManager.getSubtask(subtask2.getId());
        taskManager.getSubtask(subtask3.getId());

        System.out.println("Все задачи:");
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nВсе эпики:");
        for (Epic epic : taskManager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nПодзадачи для эпика 'Подготовка к экзамену':");
        for (Subtask subtask : taskManager.getSubtasksByEpicId(epic1.getId())) {
            System.out.println(subtask);
        }

        System.out.println("\nИстория просмотров:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

        System.out.println("\nУдаление задачи 'Купить продукты'");
        taskManager.removeTask(task1.getId());
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nУдаление эпика 'Путешествие'");
        taskManager.removeEpic(epic2.getId());
        for (Epic epic : taskManager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nИстория просмотров после удаления:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
}
