package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;

import java.util.Set;

public class RetrieveTasks {

    private final TaskRepository taskRepository;

    public RetrieveTasks(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Set<Task> retrieve(TaskStatus status){
        return taskRepository.fetch(status);
    }
}
