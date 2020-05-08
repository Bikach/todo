package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;

import java.util.Optional;


public class CreateTask {

    private final TaskRepository taskRepository;

    public CreateTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void byName(Optional<String> prefix, final String name)  {
        Task task = new Task(name);
        prefix.ifPresent(task::addPrefix);
        taskRepository.save(task);
    }
}
