package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
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

    public Set<Task> ByStatus(TaskStatus status) {
        return taskLoader.fetch()
                .stream()
                .sorted(comparingBy(status))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Comparator<? super Task> comparingBy(TaskStatus status){
        if(status.equals(TaskStatus.DONE))
            return Comparator.comparing(Task::getStatue).reversed();
        return Comparator.comparing(Task::getStatue);
    }
}
