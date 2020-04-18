package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.loaders.TaskLoader;

import java.util.Set;
import java.util.stream.Collectors;

public class FilterTheTasks {

    private final TaskLoader taskLoader;

    public FilterTheTasks(TaskLoader taskLoader) {
        this.taskLoader = taskLoader;
    }

    public Set<Task> byDoneStatus() {
        return taskLoader.fetch().stream()
                .filter(task -> task.getStatue().equals(TaskStatus.DONE))
                .collect(Collectors.toSet());
    }

    public Set<Task> byInProgressStatus() {
        return taskLoader.fetch().stream()
                .filter(task -> task.getStatue().equals(TaskStatus.IN_PROGRESS))
                .collect(Collectors.toSet());
    }
}
