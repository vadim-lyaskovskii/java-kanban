import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        System.out.println("---Добавление задач---");
        taskManager.addTask(new Task("Задача-1", "Описание-1", Status.NEW));
        taskManager.addTask(new Task("Задача-2", "Описание-2", Status.NEW));
        taskManager.addTask(new Task("Задача-3", "Описание-3", Status.NEW));
        System.out.println("************************************************************");
        System.out.println("---Вывод списка задач---");
        printAllTask(taskManager.getListAllTasks());
        System.out.println("************************************************************");
        System.out.println("---Удаление всех задач---");
        taskManager.deleteAllTasks();
        System.out.println("---Вывод списка задач после удаления---");
        printAllTask(taskManager.getListAllTasks());
        System.out.println("************************************************************");
        System.out.println("---Добавление задач---");
        taskManager.addTask(new Task("Задача-4", "Описание-4", Status.NEW));
        taskManager.addTask(new Task("Задача-5", "Описание-5", Status.NEW));
        taskManager.addTask(new Task("Задача-6", "Описание-6", Status.NEW));
        System.out.println("---Вывод списка задач---");
        printAllTask(taskManager.getListAllTasks());
        System.out.println("---Вывод задачи по идентификатору---");
        printTaskById(taskManager.getTaskById(4));
        System.out.println("************************************************************");
        System.out.println("---Обновление задачи---");
        Task task4 = taskManager.getTaskById(4);
        printUpdateTask(taskManager.updateTask(task4));
        System.out.println("************************************************************");
        System.out.println("---Изменение статуса задачи---");
        task4.setStatus(Status.IN_PROGRESS);
        printUpdateTask(taskManager.updateTask(task4));
        System.out.println("************************************************************");
        System.out.println("---Вывод списка задач---");
        printAllTask(taskManager.getListAllTasks());
        System.out.println("---Удаление задачи по идентификатору---");
        printDelTaskById(taskManager.deleteTaskById(4));
        System.out.println("---Вывод списка задач после удаления задачи по идентификатору---");
        printAllTask(taskManager.getListAllTasks());
        System.out.println("************************************************************");
        System.out.println("---Добавление эпиков---");
        taskManager.addEpic(new Epic("Эпис-7", "Описание эпика-7", Status.NEW));
        taskManager.addEpic(new Epic("Эпис-8", "Описание эпика-8", Status.NEW));
        System.out.println("---Вывод списка эпиков---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("************************************************************");
        System.out.println("---Добавление подзадач---");
        taskManager.addSubTask(new SubTask("Подзадача-9", "Описание подзадачи-9", Status.NEW, 7));
        taskManager.addSubTask(new SubTask("Подзадача-10", "Описание подзадачи-10", Status.NEW, 7));
        taskManager.addSubTask(new SubTask("Подзадача-11", "Описание подзадачи-11", Status.NEW, 8));
        taskManager.addSubTask(new SubTask("Подзадача-12", "Описание подзадачи-12", Status.NEW, 8));
        taskManager.addSubTask(new SubTask("Подзадача-13", "Описание подзадачи-14", Status.NEW, 8));
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("---Вывод списка эпиков---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("************************************************************");
        System.out.println("---Удаление всех эпиков---");
        taskManager.deleteAllEpics();
        System.out.println("---Вывод списка эпиков---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("************************************************************");
        System.out.println("---Добавление эпиков, статус DONE---");
        taskManager.addEpic(new Epic("Эпис-14", "Описание эпика-14", Status.DONE));
        taskManager.addEpic(new Epic("Эпис-15", "Описание эпика-15", Status.DONE));
        System.out.println("---Добавление подзадач статус DONE---");
        taskManager.addSubTask(new SubTask("Подзадача-16", "Описание подзадачи-16", Status.DONE, 14));
        taskManager.addSubTask(new SubTask("Подзадача-17", "Описание подзадачи-17", Status.DONE, 14));
        taskManager.addSubTask(new SubTask("Подзадача-18", "Описание подзадачи-18", Status.DONE, 15));
        taskManager.addSubTask(new SubTask("Подзадача-19", "Описание подзадачи-19", Status.DONE, 15));
        taskManager.addSubTask(new SubTask("Подзадача-20", "Описание подзадачи-20", Status.DONE, 15));
        System.out.println("---Вывод списка эпиков---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("************************************************************");
        System.out.println("---Удаление всех подзадач---");
        taskManager.deleteAllSubTasks();
        System.out.println("---Вывод списка эпиков, т.к. все подзадачи удалены статус эпиков меняется на NEW---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("************************************************************");
        System.out.println("---Добавление подзадач ---");
        taskManager.addSubTask(new SubTask("Подзадача-21", "Описание подзадачи-21", Status.NEW, 14));
        taskManager.addSubTask(new SubTask("Подзадача-22", "Описание подзадачи-22", Status.NEW, 14));
        taskManager.addSubTask(new SubTask("Подзадача-23", "Описание подзадачи-23", Status.NEW, 15));
        taskManager.addSubTask(new SubTask("Подзадача-24", "Описание подзадачи-24", Status.NEW, 15));
        taskManager.addSubTask(new SubTask("Подзадача-25", "Описание подзадачи-25", Status.NEW, 15));
        System.out.println("---Вывод списка эпиков---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("************************************************************");
        System.out.println("---Вывод эпика по идентификатору---");
        printEpicById(taskManager.getEpicById(14));
        System.out.println("************************************************************");
        System.out.println("---Вывод подзадачи по идентификатору---");
        printSubTaskById(taskManager.getSubTaskById(23));
        System.out.println("************************************************************");
        System.out.println("---Обновление подзадачи---");
        SubTask subTask24 = taskManager.getSubTaskById(24);
        printUpdateSubTask(taskManager.updateSubTask(subTask24));
        System.out.println("************************************************************");
        System.out.println("---Вывод эпика по идентификатору---");
        printEpicById(taskManager.getEpicById(15));
        System.out.println("************************************************************");
        SubTask subTask23 = taskManager.getSubTaskById(23);
        SubTask subTask25 = taskManager.getSubTaskById(25);
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        subTask23.setStatus(Status.NEW);
        subTask24.setStatus(Status.DONE);
        subTask25.setStatus(Status.IN_PROGRESS);
        System.out.println("---Изменение статусов в подзадачах---");
        printSubTask(taskManager.getListAllSubTasks());
        Epic epic15 = taskManager.getEpicById(15);
        System.out.println("---Обновление эпика---");
        printUpdateEpic(taskManager.updateEpic(epic15));
        System.out.println("---Вывод эпика по идентификатору---");
        printEpicById(taskManager.getEpicById(15));
        System.out.println("************************************************************");
        System.out.println("---Вывод списка подзадач по идентификатору эпика---");
        printSubTask(taskManager.getListSubTasksByIdEpic(15));
        System.out.println("************************************************************");
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("---Удаление подзадачи по идентификатору---");
        printDelSubTaskById(taskManager.deleteSubTaskById(22));
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("************************************************************");
        System.out.println("---Вывод списка эпиков---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
        System.out.println("---Удаление эпика по идентификатору---");
        printDelEpicById(taskManager.deleteEpicById(14));
        System.out.println("---Вывод списка эпиков---");
        printAllEpic(taskManager.getListAllEpics());
        System.out.println("---Вывод списка подзадач---");
        printSubTask(taskManager.getListAllSubTasks());
    }

    public static void printAllTask(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("Список задач пуст");
        } else {
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

    public static void printAllEpic(List<Epic> epicList) {
        if (epicList.isEmpty()) {
            System.out.println("Список эпиков пуст");
        } else {
            for (Epic epic : epicList) {
                System.out.println(epic);
            }
        }
    }

    public static void printSubTask(List<SubTask> subTaskList) {
        if (subTaskList.isEmpty()) {
            System.out.println("Список подзадач пуст");
        } else {
            for (SubTask subTask : subTaskList) {
                System.out.println(subTask);
            }
        }
    }

    public static void printTaskById(Task task) {
        if (task != null) {
            System.out.printf("Задача %s удалена", task.toString());
        } else {
            System.out.println("Задача не удалена");
        }
    }

    public static void printEpicById(Epic epic) {
        if (epic == null) {
            System.out.println("Эпик отсутствует");
        } else {
            System.out.println(epic.toString());
        }
    }

    public static void printSubTaskById(SubTask subTask) {
        if (subTask == null && subTask.getIdEpic() == 0) {
            System.out.println("Подзадача отсутствует");
        } else {
            System.out.println(subTask.toString());
        }
    }

    public static void printUpdateTask(Task task) {
        if (task == null) {
            System.out.println("Обновление задачи не выполнено");
        } else {
            System.out.printf("Задача %s обновлена \n", task.toString());
        }
    }

    public static void printUpdateSubTask(SubTask subTask) {
        if (subTask == null && subTask.getIdEpic() == 0) {
            System.out.println("Обновление подзадачи не выполнено");
        } else {
            System.out.printf("Подадача %s обновлена \n", subTask.toString());
        }
    }

    public static void printUpdateEpic(Epic epic) {
        if (epic == null) {
            System.out.println("Обновление эпика не выполнено");
        } else {
            System.out.printf("Эпик %s обновлен \n", epic.toString());
        }
    }

    public static void printDelTaskById(Task task) {
        if (task != null) {
            System.out.printf("Задача %s удалена \n", task.toString());
        } else {
            System.out.println("Удаление задачи не выполнено");
        }
    }

    public static void printDelSubTaskById(SubTask subTask) {
        if (subTask != null) {
            System.out.printf("Подзадача %s удалена \n", subTask.toString());
        } else {
            System.out.println("Удаление подзадачи не выполнено");
        }
    }

    public static void printDelEpicById(Epic epic) {
        if (epic != null) {
            System.out.printf("Эпик %s удален \n", epic.toString());
        } else {
            System.out.println("Удаление эпика не выполнено");
        }
    }
}

