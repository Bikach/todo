package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;


public class CreateTask {

    private final TaskRepository taskRepository;

    public CreateTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String name)  {
        Task task = new Task(name);
        taskRepository.save(task);
    }

    public void by(final String prefix, final String name)  {
        Task task = new Task(name);
        task.addPrefix(prefix);
        taskRepository.save(task);
    }
}
