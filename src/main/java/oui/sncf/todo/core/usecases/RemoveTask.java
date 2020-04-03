package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.exceptions.TaskDoesNotExistException;
import oui.sncf.todo.core.domain.repositories.TasksRepository;

public class RemoveTask {

    private final TasksRepository tasksRepository;

    public RemoveTask(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public boolean by(final Task task) {
        boolean doesNotExist = !tasksRepository.remove(task);
        if(doesNotExist)
            throw new TaskDoesNotExistException(task + " doesn't exist");
        return true;
    }
}
