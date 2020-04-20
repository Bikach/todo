package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterTasks {

    private final TaskLoader taskLoader;

    public FilterTasks(TaskLoader taskLoader) {
        this.taskLoader = taskLoader;
    }

    public Set<Task> byStatus(TaskStatus filter){
        return taskLoader.fetch().stream()
                .filter(task -> task.getStatue().equals(filter))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
