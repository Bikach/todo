package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SortTasks {

    private final TaskLoader taskLoader;

    public SortTasks(TaskLoader taskLoader) {
        this.taskLoader = taskLoader;
    }

    public Set<Task> ByStatusDone() {
        return taskLoader.fetch()
                .stream()
                .sorted(Comparator.comparing(Task::getStatue).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Task> byStatusInProgress() {
        return taskLoader.fetch()
                .stream()
                .sorted(Comparator.comparing(Task::getStatue))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
