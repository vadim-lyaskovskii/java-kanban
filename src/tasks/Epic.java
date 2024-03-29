package tasks;

import status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private final List<Integer> idListSubTasks = new ArrayList<>();
    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public List<Integer> getIdListSubTasks() {
        return idListSubTasks;
    }

    public void setIdListSubTask(int id) {
        idListSubTasks.add(id);
    }

    public void clearIdListSubTask() {
        idListSubTasks.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(idListSubTasks, epic.idListSubTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idListSubTasks);
    }

    @Override
    public String toString() {
        return "task.Epic{" +
                "idEpic=" + getId() +
                ", idListSubTasks=" + idListSubTasks +
                ", nameEpic='" + getName() + '\'' +
                ", descriptionEpic='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}

