package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.repositories.TasksRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class FilterTheTasks {

    private final TasksRepository tasksRepository;

    public FilterTheTasks(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Set<Task> byDoneStatus() {
        return tasksRepository.all().stream()
                .filter(task -> task.getStatue().equals(TaskStatus.DONE))
                .collect(Collectors.toSet());
    }

    public Set<Task> byInProgressStatus() {
        return tasksRepository.all().stream()
                .filter(task -> task.getStatue().equals(TaskStatus.IN_PROGRESS))
                .collect(Collectors.toSet());
    }
}
