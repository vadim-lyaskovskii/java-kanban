package tasks;

import status.Status;

import java.util.Objects;

public class SubTask extends Task {
    private final int idEpic;

    public SubTask(String nameTask, String descriptionTask, Status status, int idEpic) {
        super(nameTask, descriptionTask, status);
        this.idEpic = idEpic;
    }

    public int getIdEpic() {
        return idEpic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return idEpic == subTask.idEpic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idEpic);
    }

    @Override
    public String toString() {
        return "task.SubTask{" +
                "idSubTask=" + getId() +
                ", idEpic=" + idEpic +
                ", nameSubTask='" + getName() + '\'' +
                ", descriptionSubTask='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}

