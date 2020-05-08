package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskDoesNotExistException;

public class RetrieveTaskByName {

    private final TaskRepository taskRepository;

    public RetrieveTaskByName(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto retrieve(final String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(
                        () -> new TaskDoesNotExistException("The Task doesn't exist.")
                );
    }
}
