package manager;

import status.Status;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int id = 0;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();

    private final HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public int getNewID() {
        return ++id;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void addTask(Task task) {
        int tempId = getNewID();
        task.setId(tempId);
        tasks.put(tempId, task);
    }

    @Override
    public void addEpic(Epic epic) {
        int tempId = getNewID();
        epic.setId(tempId);
        epics.put(tempId, epic);
    }

    @Override
    public void addSubTask(SubTask subTask) {
        int tempIdSubTask = getNewID();
        int tempIdEpic = subTask.getIdEpic();
        if (epics.containsKey(tempIdEpic)) {
            subTask.setId(tempIdSubTask);
            subTasks.put(tempIdSubTask, subTask);
            epics.get(tempIdEpic).setIdListSubTask(tempIdSubTask);
        }
    }

    @Override
    public List<Task> getListAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getListAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getListAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public List<SubTask> getListSubTasksByIdEpic(int id) {
        if (epics.containsKey(id)) {
            List<SubTask> subTask = new ArrayList<>();
            for (Integer idSubTask : epics.get(id).getIdListSubTasks()) {
                subTask.add(subTasks.get(idSubTask));
            }
            return subTask;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        subTasks.clear();
        epics.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        subTasks.clear();
        if (epics.size() != 0) {
            for (Map.Entry<Integer, Epic> epicMap : epics.entrySet()) {
                epicMap.getValue().setStatus(Status.NEW);
                epicMap.getValue().clearIdListSubTask();
            }
        }
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTaskById(int id) {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public Task updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    @Override
    public SubTask updateSubTask(SubTask subTask) {
        if (subTasks.containsKey(subTask.getId())) {
            subTasks.put(subTask.getId(), subTask);
            return subTask;
        }
        return null;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateStatusEpic(epic);
            return epic;
        }
        return null;
    }

    @Override
    public void updateStatusEpic(Epic epic) {
        if (epic.getIdListSubTasks().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            int statusDone = 0;
            int statusNew = 0;
            for (Integer idSubTask : epic.getIdListSubTasks()) {
                if (subTasks.get(idSubTask).getStatus() == Status.DONE) {
                    statusDone++;
                }
                if (subTasks.get(idSubTask).getStatus() == Status.NEW) {
                    statusNew++;
                }
            }
            if (epic.getIdListSubTasks().size() == statusDone) {
                epic.setStatus(Status.DONE);
            } else if (epic.getIdListSubTasks().size() == statusNew) {
                epic.setStatus(Status.NEW);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }
    }

    @Override
    public Task deleteTaskById(int id) {
        return tasks.remove(id);
    }

    @Override
    public SubTask deleteSubTaskById(int id) {
        if (subTasks.containsKey(id)) {
            epics.get(subTasks.get(id).getIdEpic()).getIdListSubTasks().remove((Integer) id);
            return subTasks.remove(id);
        }
        return null;
    }

    @Override
    public Epic deleteEpicById(int id) {
        if (epics.containsKey(id)) {
            for (Integer idSubTask : epics.get(id).getIdListSubTasks()) {
                subTasks.remove(idSubTask);
            }
            epics.get(id).clearIdListSubTask();
            return epics.remove(id);
        }
        return null;
    }
}

