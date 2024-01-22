package test;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import status.Status;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private static TaskManager taskManager;
    private static Epic epic;
    private static Task task;
    private static SubTask subTask;
    private static int idEpic;

    @BeforeEach
    public void taskManagerTest(){
        taskManager = Managers.getDefault();
        epic = new Epic("TЕСТ-EPIC", "ТЕСТ-EPIC", Status.NEW);
        task = new Task("TЕСТ-TASK", "ТЕСТ-TASK", Status.NEW);
        taskManager.addEpic(epic);
        idEpic = epic.getId();
        subTask = new SubTask("TЕСТ-SUBTASK", "ТЕСТ-SUBTASK", Status.NEW, idEpic);
        taskManager.addSubTask(subTask);
    }

    @Test
    void teatTaskEqualToEachOther() {
        taskManager.addTask(task);
        int id = task.getId();
        Task task2 = taskManager.getTaskById(id);
        assertEquals(task, task2, "Task не равны друг другу");
    }

    @Test
    void testEpicEqualToEachOther() {
        Epic epic2 = taskManager.getEpicById(idEpic);
        assertEquals(epic, epic2, "Epic не равны друг другу");
    }

    @Test
    void testSubTaskEqualToEachOther() {
        int idSubTask = subTask.getId();
        SubTask subTask2 = taskManager.getSubTaskById(idSubTask);
        assertEquals(subTask, subTask2, "SubTask не равны друг другу");
    }

    @Test
    void testEpicCannotBeAddedToItselfAsASubtask() {
        SubTask subTask2 = new SubTask("TЕСТ7", "ТЕСТ7", Status.NEW, idEpic);
        taskManager.addSubTask(subTask2);
        for (Integer epic : epic.getIdListSubTasks()) {
            assertNotEquals(idEpic, epic, "Epic можно добавить в самого себя в виде подзадачи");
        }
    }

    @Test
    void testSubtaskCannotBeMadeYourOwnEpic() {
        int idEpicBySubTask1 = subTask.getIdEpic();
        SubTask subTask2 = new SubTask("TЕСТ10", "ТЕСТ10", Status.NEW, idEpic);
        taskManager.addSubTask(subTask2);
        int idEpicBySubTask2 = subTask2.getIdEpic();
        assertEquals(idEpic, idEpicBySubTask1, "Subtask можно сделать своим же эпиком");
        assertEquals(idEpic, idEpicBySubTask2, "Subtask можно сделать своим же эпиком");
    }

    @Test
    void testUtilityClassAlwaysReturnsInitializedInstancesOfManagers() {
        assertNotNull(taskManager, "Экземпляры менеджеров NULL");
    }

    @Test
    void testTasksOfDifferentTypesAndCanFindThemById() {
        int idSubTask = subTask.getId();
        taskManager.addTask(task);
        int idTask = task.getId();
        assertEquals(epic, taskManager.getEpicById(idEpic), "Задача типа Epic нельзя найти по id");
        assertEquals(subTask, taskManager.getSubTaskById(idSubTask), "Задача типа SubTask нельзя найти по id");
        assertEquals(task, taskManager.getTaskById(idTask), "Задача типа Task нельзя найти по id");
    }

    @Test
    void testIdDoNotConflictWithinTheManager() {
        taskManager.addTask(task);
        int id1 = task.getId();
        taskManager.updateTask(task);
        int id2 = task.getId();
        assertEquals(id1, id2, "id конфликтуют");
    }

    @Test
    void testHistoryManagerSavesThePreviousVersionOfTheTask() {
        taskManager.addTask(task);
        int id1 = task.getId();
        taskManager.getTaskById(id1);
        Task task2 = new Task("TЕСТ16", "ТЕСТ16", Status.NEW);
        taskManager.addTask(task2);
        int id2 = task2.getId();
        taskManager.getTaskById(id2);
        int l = taskManager.getHistory().size() - 2;
        assertEquals(task, taskManager.getHistory().get(l), "Предыдущая версия задачи несохранена");
    }
}