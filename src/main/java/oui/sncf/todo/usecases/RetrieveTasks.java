package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RetrieveTasks {

    private final TaskRepository taskRepository;

    public RetrieveTasks(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Set<Task> fetch(TaskStatus filter){
        return (filter == null) ?
                taskRepository.fetch() :
                taskRepository.fetch().stream()
                        .filter(task -> task.getStatus().equals(filter))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
