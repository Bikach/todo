package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Optional;
import java.util.Set;

public class RetrieveTasks {

    private final TaskRepository taskRepository;

    public RetrieveTasks(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Set<TaskDto> retrieve(Optional<TaskStatus> status){
        return taskRepository.fetch(status);
    }
}