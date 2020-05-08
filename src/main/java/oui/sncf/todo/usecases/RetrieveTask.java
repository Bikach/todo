package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskDoesNotExistException;

public class RetrieveTask {

    private final TaskRepository taskRepository;

    public RetrieveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto byName(final String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(
                        () -> new TaskDoesNotExistException("The Task doesn't exist.")
                );
    }
}
