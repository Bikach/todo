package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskDoesNotExistException;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;

public class RemoveTask {

    private final TaskRepository taskRepository;

    public RemoveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean by(final Task task) {
        if(taskRepository.remove(task)) return true;
        throw new TaskDoesNotExistException(task + " doesn't exist");
    }
}
