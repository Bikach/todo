package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;
import oui.sncf.todo.core.usecases.exceptions.TaskDoesNotExistException;

public class RetrieveTaskByName {

    private final TaskRepository taskRepository;

    public RetrieveTaskByName(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task get(String nameTask){
        return taskRepository.get(nameTask)
                .orElseThrow(
                        () -> new TaskDoesNotExistException(nameTask + " doesn't exist")
                );
    }

}
