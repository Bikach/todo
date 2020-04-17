package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskAlReadyExistException;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;

public class CreateTaskByName {

    private final TaskRepository taskRepository;

    public CreateTaskByName(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task of(final String name)  {
        Task task = new Task(name);
        if (taskRepository.save(task)) return task;
        throw new TaskAlReadyExistException(task + " already exist");
    }
}
