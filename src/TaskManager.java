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
        task.setIdTask(tempId);
        tasks.put(tempId, task);
    }

    public void addEpic (Epic epic) {
        int tempId = getNewID();
        epic.setIdEpic(tempId);
        epics.put(tempId, epic);
    }

    public void addSubTask (SubTask subTask) {
        int tempIdSubTask = getNewID();
        int tempIdEpic = subTask.getIdEpic();
        if (epics.containsKey(tempIdEpic)) {
            subTask.setIdSubTask(tempIdSubTask);
            subTasks.put(tempIdSubTask, subTask);
            epics.get(tempIdEpic).setIdListSubTask(tempIdSubTask);
        }
    }

    public List<Task> getListAllTasks() {
        if (tasks.size() == 0) {
            return Collections.emptyList();
        }
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getListAllEpics() {
        if (epics.size() == 0) {
            return Collections.emptyList();
        }
        return new ArrayList<>(epics.values());
    }

    public List<SubTask> getListAllSubTasks() {
        if (subTasks.size() == 0) {
            return Collections.emptyList();
        }
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
        for (Map.Entry<Integer, Epic> epicMap : epics.entrySet()) {
            epicMap.getValue().clearIdListSubTask();
        }
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
        if(tasks.containsKey(id)) {
            return tasks.get(id);
        } else {
            return new Task(null,null,null);
        }
    }

    public Epic getEpicById(int id) {
        if(epics.containsKey(id)) {
            return epics.get(id);
        } else {
            return new Epic(null,null,null);
        }
    }

    public SubTask getSubTaskById(int id) {
        if (subTasks.containsKey(id)) {
            return subTasks.get(id);
        } else {
            return new SubTask(null, null, null, 0);
        }
    }

    public Task updateTask(Task task) {
        if (tasks.containsKey(task.getIdTask())) {
            tasks.put(task.getIdTask(), task);
            return task;
        } else {
            return new Task(null,null,null);
        }
    }

    public SubTask updateSubTask(SubTask subTask) {
        if (subTasks.containsKey(subTask.getIdSubTask())) {
            subTasks.put(subTask.getIdSubTask(), subTask);
            return subTask;
        } else {
            return new SubTask(null,null,null, 0);
        }
    }

    public Epic updateEpic(Epic epic) {
        if (epics.containsKey(epic.getIdEpic())) {
            epics.put(epic.getIdEpic(), epic);
            updateStatusEpic(epic);
            return epic;
        } else {
            return new Epic(null,null,null);
        }
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
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return null;
        } else {
            return new Task(null,null,null);
        }
    }

    public SubTask deleteSubTaskById (int id) {
        if (subTasks.containsKey(id)) {
            subTasks.remove(id);
            return null;
        } else {
            return new SubTask(null,null,null, 0);
        }
    }

    public Epic deleteEpicById (int id) {
        if (epics.containsKey(id)) {
            for(Integer idSubTask: epics.get(id).getIdListSubTasks()) {
                subTasks.remove(idSubTask);
            }
            epics.get(id).clearIdListSubTask();
            epics.remove(id);
            return null;
        } else {
            return new Epic(null,null,null);
        }
    }

}

