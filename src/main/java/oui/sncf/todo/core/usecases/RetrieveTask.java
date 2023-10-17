package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.exception.TaskDoesNotExistException;

public class RetrieveTask {

    private final TaskRepository taskRepository;

    public RetrieveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task byName(final String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(
                        () -> new TaskDoesNotExistException("The Task doesn't exist.")
                );
    }
}
