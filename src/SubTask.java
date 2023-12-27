import java.util.Objects;

public class SubTask extends Task {
    private int idSubTask;
    private final int idEpic;

    public SubTask(String nameTask, String descriptionTask, Status status, int idEpic) {
        super(nameTask, descriptionTask, status);
        this.idEpic = idEpic;
    }

    public int getIdSubTask() {
        return idSubTask;
    }

    public void setIdSubTask(int idSubTask) {
        this.idSubTask = idSubTask;
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
        return idSubTask == subTask.idSubTask && idEpic == subTask.idEpic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSubTask, idEpic);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "idSubTask=" + idSubTask +
                ", idEpic=" + idEpic +
                ", nameSubTask='" + getName() + '\'' +
                ", descriptionSubTask='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}

