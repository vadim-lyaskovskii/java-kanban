package manager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> historyTask = new ArrayList<>();
    private static final int MAX_HISTORY = 10;

    @Override
    public void add(Task task) {
        if (historyTask.size() >= MAX_HISTORY) {
            historyTask.remove(0);
            historyTask.add(task);
        } else {
            historyTask.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyTask;
    }
}
