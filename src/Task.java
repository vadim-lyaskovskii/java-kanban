import java.util.Objects;

public class Task {
    private final String name;
    private final String description;
    Status status;
    private int idTask;

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask && name.equals(task.name)
                && description.equals(task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, idTask);
    }

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", nameTask='" + name + '\'' +
                ", descriptionTask='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}

