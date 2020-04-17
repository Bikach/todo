package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.port.loaders.TaskLoader;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SortTasksByStatusDone {

    private final TaskLoader taskLoader;

    public SortTasksByStatusDone(TaskLoader taskLoader) {
        this.taskLoader = taskLoader;
    }

    public Set<Task> sort() {
        return taskLoader.fetch()
                .stream()
                .sorted(Comparator.comparing(Task::getStatue).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
