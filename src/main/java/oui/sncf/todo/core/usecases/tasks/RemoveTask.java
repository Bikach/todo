package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskDoesNotExistException;
import oui.sncf.todo.core.domain.tasks.port.TaskRepository;

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
