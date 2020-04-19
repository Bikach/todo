package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskAlreadyExistException;
import oui.sncf.todo.core.domain.tasks.port.TaskRepository;


public class CreateTaskByName {

    private final TaskRepository taskRepository;

    public CreateTaskByName(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task of(final String name)  {
        Task task = new Task(name);
        if (taskRepository.save(task)) return task;
        throw new TaskAlreadyExistException(task + " already exist");
    }
}
