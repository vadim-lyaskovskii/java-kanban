import java.util.*;

public class TaskManager {
    private int id = 0;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();

    public int getNewID() {
        return ++id;
    }

    public void addTask (Task task) {
        int tempId = getNewID();
        task.setId(tempId);
        tasks.put(tempId, task);
    }

    public void addEpic (Epic epic) {
        int tempId = getNewID();
        epic.setId(tempId);
        epics.put(tempId, epic);
    }

    public void addSubTask (SubTask subTask) {
        int tempIdSubTask = getNewID();
        int tempIdEpic = subTask.getIdEpic();
        if (epics.containsKey(tempIdEpic)) {
            subTask.setId(tempIdSubTask);
            subTasks.put(tempIdSubTask, subTask);
            epics.get(tempIdEpic).setIdListSubTask(tempIdSubTask);
        }
    }

    public List<Task> getListAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getListAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<SubTask> getListAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

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

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        subTasks.clear();
        epics.clear();
    }

    public void deleteAllSubTasks() {
        subTasks.clear();
        if (epics.size() != 0) {
            for (Map.Entry<Integer, Epic> epicMap : epics.entrySet()) {
                epicMap.getValue().setStatus(Status.NEW);
                epicMap.getValue().clearIdListSubTask();
            }
        }
    }

    public Task getTaskById(int id) {
            return tasks.get(id);
    }

    public Epic getEpicById(int id) {
            return epics.get(id);
    }

    public SubTask getSubTaskById(int id) {
            return subTasks.get(id);
    }

    public Task updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public SubTask updateSubTask(SubTask subTask) {
        if (subTasks.containsKey(subTask.getId())) {
            subTasks.put(subTask.getId(), subTask);
            return subTask;
        }
        return null;
    }

    public Epic updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateStatusEpic(epic);
            return epic;
        }
        return null;
    }

    private void updateStatusEpic (Epic epic) {
        if (epic.getIdListSubTasks().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            int statusDone = 0;
            int statusNew = 0;
            for (Integer idSubTask : epic.getIdListSubTasks()){
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

    public Task deleteTaskById (int id) {
            return tasks.remove(id);
    }

    public SubTask deleteSubTaskById (int id) {
        if (subTasks.containsKey(id)) {
            epics.get(subTasks.get(id).getIdEpic()).getIdListSubTasks().remove((Integer) id);
            return subTasks.remove(id);
        }
        return null;
    }

    public Epic deleteEpicById (int id) {
        if (epics.containsKey(id)) {
            for(Integer idSubTask: epics.get(id).getIdListSubTasks()) {
                subTasks.remove(idSubTask);
            }
            epics.get(id).clearIdListSubTask();
            return epics.remove(id);
        }
        return null;
    }
}

