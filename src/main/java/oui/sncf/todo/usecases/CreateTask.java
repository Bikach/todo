package oui.sncf.todo.usecases;

import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskAlreadyExistException;
import oui.sncf.todo.core.port.TaskRepository;


public class CreateTask {

    private final TaskRepository taskRepository;

    public CreateTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task of(final String name)  {
        Task task = new Task(name);
        if (taskRepository.save(task)) return task;
        throw new TaskAlreadyExistException(task + " already exist");
    }
}
