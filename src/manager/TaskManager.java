package manager;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getHistory();
    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubTask(SubTask subTask);

    List<Task> getListAllTasks();

    List<Epic> getListAllEpics();

    List<SubTask> getListAllSubTasks();

    List<SubTask> getListSubTasksByIdEpic(int id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubTaskById(int id);

    Task updateTask(Task task);

    SubTask updateSubTask(SubTask subTask);

    Epic updateEpic(Epic epic);

    void updateStatusEpic(Epic epic);

    Task deleteTaskById(int id);

    SubTask deleteSubTaskById(int id);

    Epic deleteEpicById(int id);
}
