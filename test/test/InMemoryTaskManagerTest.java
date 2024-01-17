package test;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import status.Status;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private static TaskManager taskManager;

    @BeforeAll
    public static void taskManagerTest(){
        taskManager = Managers.getDefault(Managers.getDefaultHistory());
    }

    @Test
    void teat1TaskEqualToEachOther() {
        Task task1 = new Task("TЕСТ1", "ТЕСТ1", Status.NEW);
        taskManager.addTask(task1);
        int id = task1.getId();
        Task task2 = taskManager.getTaskById(id);
        assertEquals(task1, task2, "Task не равны друг другу");
    }

    @Test
    void test2EpicEqualToEachOther() {
        Epic epic1 = new Epic("TЕСТ2", "ТЕСТ2", Status.NEW);
        taskManager.addEpic(epic1);
        int id = epic1.getId();
        Epic epic2 = taskManager.getEpicById(id);
        assertEquals(epic1, epic2, "Epic не равны друг другу");
    }

    @Test
    void test3SubTaskEqualToEachOther() {
        Epic epic = new Epic("TЕСТ3", "ТЕСТ3", Status.NEW);
        taskManager.addEpic(epic);
        int idEpic = epic.getId();
        SubTask subTask1 = new SubTask("TЕСТ4", "ТЕСТ4", Status.NEW, idEpic);
        taskManager.addSubTask(subTask1);
        int idSubTask = subTask1.getId();
        SubTask subTask2 = taskManager.getSubTaskById(idSubTask);
        assertEquals(subTask1, subTask2, "SubTask не равны друг другу");
    }

    @Test
    void test4EpicCannotBeAddedToItselfAsASubtask() {
        Epic epic1 = new Epic("TЕСТ5", "ТЕСТ5", Status.NEW);
        taskManager.addEpic(epic1);
        int idEpic1 = epic1.getId();
        SubTask subTask1 = new SubTask("TЕСТ6", "ТЕСТ6", Status.NEW, idEpic1);
        taskManager.addSubTask(subTask1);
        SubTask subTask2 = new SubTask("TЕСТ7", "ТЕСТ7", Status.NEW, idEpic1);
        taskManager.addSubTask(subTask2);
        for (Integer epic : epic1.getIdListSubTasks()) {
            assertNotEquals(idEpic1, epic, "Epic можно добавить в самого себя в виде подзадачи");
        }
    }

    @Test
    void test5SubtaskCannotBeMadeYourOwnEpic() {
        Epic epic1 = new Epic("TЕСТ8", "ТЕСТ8", Status.NEW);
        taskManager.addEpic(epic1);
        int idEpic1 = epic1.getId();
        SubTask subTask1 = new SubTask("TЕСТ9", "ТЕСТ9", Status.NEW, idEpic1);
        taskManager.addSubTask(subTask1);
        int idEpicBySubTask1 = subTask1.getIdEpic();
        SubTask subTask2 = new SubTask("TЕСТ10", "ТЕСТ10", Status.NEW, idEpic1);
        taskManager.addSubTask(subTask2);
        int idEpicBySubTask2 = subTask2.getIdEpic();
        assertEquals(idEpic1, idEpicBySubTask1, "Subtask можно сделать своим же эпиком");
        assertEquals(idEpic1, idEpicBySubTask2, "Subtask можно сделать своим же эпиком");
    }

    @Test
    void test6UtilityClassAlwaysReturnsInitializedInstancesOfManagers() {
        assertNotNull(taskManager, "Экземпляры менеджеров NULL");
    }

    @Test
    void test7TasksOfDifferentTypesAndCanFindThemById() {
        Epic epic = new Epic("TЕСТ11", "ТЕСТ11", Status.NEW);
        taskManager.addEpic(epic);
        int idEpic = epic.getId();
        SubTask subTask = new SubTask("TЕСТ12", "ТЕСТ12", Status.NEW, idEpic);
        taskManager.addSubTask(subTask);
        int idSubTask = subTask.getId();
        Task task = new Task("TЕСТ13", "ТЕСТ13", Status.NEW);
        taskManager.addTask(task);
        int idTask = task.getId();
        assertEquals(epic, taskManager.getEpicById(idEpic), "Задача типа Epic нельзя найти по id");
        assertEquals(subTask, taskManager.getSubTaskById(idSubTask), "Задача типа SubTask нельзя найти по id");
        assertEquals(task, taskManager.getTaskById(idTask), "Задача типа Task нельзя найти по id");
    }

    @Test
    void test8IdDoNotConflictWithinTheManager() {
        Task task = new Task("TЕСТ14", "ТЕСТ14", Status.NEW);
        taskManager.addTask(task);
        int id1 = task.getId();
        taskManager.updateTask(task);
        int id2 = task.getId();
        assertEquals(id1, id2, "id конфликтуют");
    }

    @Test
    void test9HistoryManagerSavesThePreviousVersionOfTheTask() {
        Task task1 = new Task("TЕСТ15", "ТЕСТ15", Status.NEW);
        taskManager.addTask(task1);
        int id1 = task1.getId();
        taskManager.getTaskById(id1);
        Task task2 = new Task("TЕСТ16", "ТЕСТ16", Status.NEW);
        taskManager.addTask(task2);
        int id2 = task2.getId();
        taskManager.getTaskById(id2);
        int l = taskManager.getHistory().size() - 2;
        assertEquals(task1, taskManager.getHistory().get(l), "Предыдущая версия задачи несохранена");
    }


}